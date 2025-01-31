package com.example.arfid

import androidx.compose.runtime.mutableStateListOf
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Datenklassen für Beiträge und Kommentare
data class Post(
    val id: Int,
    val title: String,
    val content: String,
    val author: String,
    val timestamp: String
)

data class Comment(
    val postId: Int,
    val content: String,
    val author: String,
    val timestamp: String
)

// Klasse zur Verwaltung der Forum-Daten
class ForumDaten {

    // MutableStateLists, um Änderungen automatisch in der UI zu aktualisieren
    private val posts = mutableStateListOf<Post>()
    private val comments = mutableStateListOf<Comment>()

    // Beiträge abrufen
    fun getPosts(): List<Post> {
        return posts
    }

    // Kommentare zu einem Beitrag abrufen
    fun getCommentsForPost(postId: Int): List<Comment> {
        return comments.filter { it.postId == postId }
    }

    // Beitrag hinzufügen
    fun addPost(title: String, content: String, author: String) {
        val id = posts.size + 1
        val timestamp = getCurrentTimestamp()
        val post = Post(id, title, content, author, timestamp)
        posts.add(post)
    }

    // Kommentar zu einem Beitrag hinzufügen
    fun addComment(postId: Int, content: String, author: String) {
        val timestamp = getCurrentTimestamp()
        val comment = Comment(postId, content, author, timestamp)
        comments.add(comment)
    }

    // Beispiel-Daten
    fun loadSampleData() {
        addPost(
            title = "Frage zum Essverhalten im Kleinkindalter",
            content = "Wie kann ich mein 5-jähriges Kind davon überzeugen, dass es nicht nur Pommes essen kann?",
            author = "Lisa89"
        )
        addPost(
            title = "Matt, müde und schlapp",
            content = "Mein Kind verweigert jegliches Fleisch und ist ständig müde. Kann es sein, dass sie in einen Eisenmangel gerutscht ist?",
            author = "20Bobby01"
        )
        addComment(
            postId = 1,
            content = "Das ist eine tolle Frage! Habt ihr schon mal Pommes selbst gemacht? Vielleicht sieht dein Kind, dass es aus Kartoffeln gemacht ist, und das Kartoffeln echt lecker sind.",
            author = "Charlie-der-Chefkoch"
        )
        addComment(
            postId = 2,
            content = "Lass das lieber vom Kinderarzt abklären. Ein Eisenmangel kann Müdigkeit verursachen.",
            author = "Diana"
        )
    }

    // Zeitstempel für Beiträge und Kommentare erstellen
    private fun getCurrentTimestamp(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }
}
