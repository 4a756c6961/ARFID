package com.example.arfid

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

class Forum {
    private val posts = mutableListOf<Post>()
    private val comments = mutableListOf<Comment>()

    // Beitrag hinzufügen
    fun addPost(title: String, content: String, author: String) {
        val id = posts.size + 1
        val timestamp = System.currentTimeMillis().toString()
        val post = Post(id, title, content, author, timestamp)
        posts.add(post)
         println("Post hinzugefügt: $post")
    }

    // Kommentar zu einem Beitrag hinzufügen
    fun addComment(postId: Int, content: String, author: String) {
        val timestamp = System.currentTimeMillis().toString()
        val comment = Comment(postId, content, author, timestamp)
        comments.add(comment)
        println("Kommentar hinzugefügt: $comment")
    }

    // Alle Beiträge abrufen
    fun getPosts(): List<Post> {
        return posts
    }
}