package joller

class Mens {

    String naam
    String geslag
    static constraints = {
        naam(size:0..30, blank: false)
        naam(inList: ['Man', 'Vrou'])

    }
}
