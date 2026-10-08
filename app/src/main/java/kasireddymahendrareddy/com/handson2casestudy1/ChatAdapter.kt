package kasireddymahendrareddy.com.handson2casestudy1

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val messages: List<Message>) : RecyclerView.Adapter<ChatAdapter.MessageViewHolder>() {

    class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageText: TextView = view.findViewById(R.id.messageText)
        val container: LinearLayout = view as LinearLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.messageText.text = message.text

        val layoutParams = holder.messageText.layoutParams as LinearLayout.LayoutParams
        if (message.isUser) {
            layoutParams.gravity = Gravity.END
            holder.messageText.setBackgroundResource(R.drawable.bg_user_message)
            holder.messageText.setTextColor(ContextCompat.getColor(holder.itemView.context, android.R.color.white))
        } else {
            layoutParams.gravity = Gravity.START
            holder.messageText.setBackgroundResource(R.drawable.bg_bot_message)
            holder.messageText.setTextColor(ContextCompat.getColor(holder.itemView.context, android.R.color.black))
        }
        holder.messageText.layoutParams = layoutParams
    }

    override fun getItemCount() = messages.size
}
