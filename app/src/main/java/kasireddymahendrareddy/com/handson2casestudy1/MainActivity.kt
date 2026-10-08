package kasireddymahendrareddy.com.handson2casestudy1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ChatAdapter
    private val messages = mutableListOf<Message>()
    
    // Placeholder for an API key if using an external service
    private val CHATBOT_API_KEY = "YOUR_API_KEY_HERE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val messageInput = findViewById<EditText>(R.id.messageInput)
        val sendButton = findViewById<Button>(R.id.sendButton)

        adapter = ChatAdapter(messages)
        recyclerView.adapter = adapter

        // Initial Greeting
        addMessage("Hello! I'm your Customer Support Assistant. How can I help you today?", false)

        sendButton.setOnClickListener {
            val text = messageInput.text.toString().trim()
            if (text.isNotEmpty()) {
                addMessage(text, true)
                messageInput.text.clear()
                
                // Simulate bot response after a short delay
                val response = getBotResponse(text)
                recyclerView.postDelayed({
                    addMessage(response, false)
                }, 500)
            }
        }
    }

    private fun addMessage(text: String, isUser: Boolean) {
        messages.add(Message(text, isUser))
        adapter.notifyItemInserted(messages.size - 1)
        findViewById<RecyclerView>(R.id.recyclerView).scrollToPosition(messages.size - 1)
    }

    /**
     * Intent detection logic for common customer queries.
     */
    private fun getBotResponse(query: String): String {
        val input = query.lowercase()

        return when {
            input.contains("order") -> {
                "To track your order, please provide your Order ID. You can also view status in 'My Orders' section."
            }
            input.contains("refund") || input.contains("return") -> {
                "Our return policy allows returns within 30 days of purchase. Would you like to start a return process?"
            }
            input.contains("hours") || input.contains("time") -> {
                "Our support team is available Monday to Friday, 9 AM to 6 PM EST."
            }
            input.contains("contact") || input.contains("agent") || input.contains("human") -> {
                "I can connect you to a live agent. Please wait a moment or call us at 1-800-SUPPORT."
            }
            input.contains("hello") || input.contains("hi") -> {
                "Hi there! How can I assist you with your shopping today?"
            }
            input.contains("sql") || input.contains("student") -> {
                "To query students, you can use: SELECT * FROM students;"
            }
            else -> {
                // Fallback handling
                "I'm sorry, I didn't quite understand that. Could you please rephrase your query? You can ask about orders, refunds, store hours, or SQL queries."
            }
        }
    }
}
