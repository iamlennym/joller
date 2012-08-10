package joller



import org.junit.*
import grails.test.mixin.*

@TestFor(MensController)
@Mock(Mens)
class MensControllerTests {


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/mens/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.mensInstanceList.size() == 0
        assert model.mensInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.mensInstance != null
    }

    void testSave() {
        controller.save()

        assert model.mensInstance != null
        assert view == '/mens/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/mens/show/1'
        assert controller.flash.message != null
        assert Mens.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/mens/list'


        populateValidParams(params)
        def mens = new Mens(params)

        assert mens.save() != null

        params.id = mens.id

        def model = controller.show()

        assert model.mensInstance == mens
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/mens/list'


        populateValidParams(params)
        def mens = new Mens(params)

        assert mens.save() != null

        params.id = mens.id

        def model = controller.edit()

        assert model.mensInstance == mens
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/mens/list'

        response.reset()


        populateValidParams(params)
        def mens = new Mens(params)

        assert mens.save() != null

        // test invalid parameters in update
        params.id = mens.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/mens/edit"
        assert model.mensInstance != null

        mens.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/mens/show/$mens.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        mens.clearErrors()

        populateValidParams(params)
        params.id = mens.id
        params.version = -1
        controller.update()

        assert view == "/mens/edit"
        assert model.mensInstance != null
        assert model.mensInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/mens/list'

        response.reset()

        populateValidParams(params)
        def mens = new Mens(params)

        assert mens.save() != null
        assert Mens.count() == 1

        params.id = mens.id

        controller.delete()

        assert Mens.count() == 0
        assert Mens.get(mens.id) == null
        assert response.redirectedUrl == '/mens/list'
    }
}
