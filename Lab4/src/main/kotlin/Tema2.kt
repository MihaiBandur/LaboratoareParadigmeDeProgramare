package org.example

data class Movie(val title: String, val duration: Int, val price: Double)

class Theater(private val movie: Movie, private val totalSeats: Int) {
    private val availableSeats = mutableSetOf<Int>()

    init {
        availableSeats.addAll(1..totalSeats)
    }

    fun showAvailableSeats(): Set<Int> = availableSeats

    fun bookSeat(seat: Int): Boolean {
        return if (availableSeats.contains(seat)) {
            availableSeats.remove(seat)
            true
        } else {
            false
        }
    }
}

data class Ticket(val movie: Movie, val seatNumber: Int)

interface PaymentProcessor {
    fun processPayment(amount: Double): Boolean
}

class CardPayment : PaymentProcessor {
    override fun processPayment(amount: Double): Boolean {
        println("Plata cu cardul efectuată: $amount RON")
        return true
    }
}

class CashPayment : PaymentProcessor {
    override fun processPayment(amount: Double): Boolean {
        println("Plata cash efectuată: $amount RON")
        return true
    }
}

class User(val name: String, private val paymentMethod: PaymentProcessor) {
    fun buyTicket(theater: Theater, seat: Int, movie: Movie): Ticket? {
        return if (theater.bookSeat(seat)) {
            if (paymentMethod.processPayment(movie.price)) {
                Ticket(movie, seat)
            } else {
                println("Eroare la plată.")
                null
            }
        } else {
            println("Locul $seat nu este disponibil.")
            null
        }
    }
}

class CinemaBookingSystem {
    private val movies = mutableListOf<Movie>()
    private val theaters = mutableMapOf<Movie, Theater>()

    fun addMovie(movie: Movie, seats: Int) {
        movies.add(movie)
        theaters[movie] = Theater(movie, seats)
    }

    fun showMovies() {
        movies.forEachIndexed { index, movie ->
            println("${index + 1}. ${movie.title} - ${movie.duration} min - ${movie.price} RON")
        }
    }

    fun getTheaterForMovie(movie: Movie): Theater? {
        return theaters[movie]
    }
}

fun main() {
    val cinemaSystem = CinemaBookingSystem()

    val movie1 = Movie("Inception", 148, 30.0)
    val movie2 = Movie("Interstellar", 169, 35.0)

    cinemaSystem.addMovie(movie1, 5)
    cinemaSystem.addMovie(movie2, 3)

    println("Filme disponibile:")
    cinemaSystem.showMovies()

    val user = User("Mihai", CardPayment())

    val theater = cinemaSystem.getTheaterForMovie(movie1)
    if (theater != null) {
        val ticket = user.buyTicket(theater, 2, movie1)
        println("Bilet cumpărat: $ticket")
    }
}