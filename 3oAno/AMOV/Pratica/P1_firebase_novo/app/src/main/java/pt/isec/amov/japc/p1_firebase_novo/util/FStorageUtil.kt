package pt.isec.amov.japc.p1_firebase_novo.util

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FStorageUtil {

    companion object {
        fun addDataToFirestore(onResult: (Throwable?) -> Unit) {
            val db = Firebase.firestore
            val scores = hashMapOf(
                "nrgames" to 0,
                "topscore" to 0
            )
            db.collection ("Scores").document("Level1").set(scores)
                .addOnCompleteListener{ result ->
                    onResult(result.exception)
                }
        }
    }

}