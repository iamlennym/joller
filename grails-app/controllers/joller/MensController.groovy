package joller

import org.springframework.dao.DataIntegrityViolationException

class MensController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [mensInstanceList: Mens.list(params), mensInstanceTotal: Mens.count()]
    }

    def create() {
        [mensInstance: new Mens(params)]
    }

    def save() {
        def mensInstance = new Mens(params)
        if (!mensInstance.save(flush: true)) {
            render(view: "create", model: [mensInstance: mensInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'mens.label', default: 'Mens'), mensInstance.id])
        redirect(action: "show", id: mensInstance.id)
    }

    def show() {
        def mensInstance = Mens.get(params.id)
        if (!mensInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'mens.label', default: 'Mens'), params.id])
            redirect(action: "list")
            return
        }

        [mensInstance: mensInstance]
    }

    def edit() {
        def mensInstance = Mens.get(params.id)
        if (!mensInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mens.label', default: 'Mens'), params.id])
            redirect(action: "list")
            return
        }

        [mensInstance: mensInstance]
    }

    def update() {
        def mensInstance = Mens.get(params.id)
        if (!mensInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mens.label', default: 'Mens'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (mensInstance.version > version) {
                mensInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'mens.label', default: 'Mens')] as Object[],
                          "Another user has updated this Mens while you were editing")
                render(view: "edit", model: [mensInstance: mensInstance])
                return
            }
        }

        mensInstance.properties = params

        if (!mensInstance.save(flush: true)) {
            render(view: "edit", model: [mensInstance: mensInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'mens.label', default: 'Mens'), mensInstance.id])
        redirect(action: "show", id: mensInstance.id)
    }

    def delete() {
        def mensInstance = Mens.get(params.id)
        if (!mensInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'mens.label', default: 'Mens'), params.id])
            redirect(action: "list")
            return
        }

        try {
            mensInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'mens.label', default: 'Mens'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'mens.label', default: 'Mens'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
