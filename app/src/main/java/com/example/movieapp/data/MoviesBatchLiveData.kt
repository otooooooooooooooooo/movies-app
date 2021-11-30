package com.example.movieapp.data

import androidx.lifecycle.MutableLiveData
import com.example.movieapp.database.Database
import com.example.movieapp.models.Movie
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class MoviesBatchLiveData : MutableLiveData<MutableList<Movie>>() {
    private var moviesReference = Database.getMoviesReference()

    fun getBatch(): MoviesBatchLiveData {
        moviesReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Load movies from the database
                // TODO: test if movies loaded
                val tmpMoviesList = mutableListOf<Movie>()
                snapshot.children.elementAt(SessionData.currentBatchIndex).children.forEach {
                    if (it != null) {
                        tmpMoviesList.add(it.getValue<Movie>()!!)
                    }
                }

                // Update data in LiveData
                value = tmpMoviesList
            }

            override fun onCancelled(error: DatabaseError) {}
        })

        return this
    }

    fun setSwiped(movie: Movie, isAccepted: Boolean = false) {
        movie.swipedBy.add(SessionData.deviceId!!)
        moviesReference.child(SessionData.currentBatchUid).child(movie.uid!!).child("swipedBy")
            .setValue(movie.swipedBy)

        if (isAccepted) {
            movie.acceptedBy.add(SessionData.deviceId!!)
            moviesReference.child(SessionData.currentBatchUid).child(movie.uid!!)
                .child("acceptedBy")
                .setValue(movie.acceptedBy)
        }

    }
}