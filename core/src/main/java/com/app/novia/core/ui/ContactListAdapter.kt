package com.app.novia.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.app.novia.core.R
import com.app.novia.core.domain.model.EmergencyContactEntity
import com.daimajia.swipe.adapters.BaseSwipeAdapter
import de.hdodenhof.circleimageview.CircleImageView

class ContactListAdapter : BaseSwipeAdapter() {

    private lateinit var onDeleteClickCallback: OnDeleteClickCallback
    private lateinit var onEditClickCallback: OnEditClickCallback
    private var listData = ArrayList<EmergencyContactEntity>()

    fun setOnDeleteCallback(onDeleteClickCallback: OnDeleteClickCallback) {
        this.onDeleteClickCallback = onDeleteClickCallback
    }

    fun setOnEditCallback(onEditClickCallback: OnEditClickCallback) {
        this.onEditClickCallback = onEditClickCallback
    }

    fun setData(listContact: ArrayList<EmergencyContactEntity>?) {
        if (listContact == null) return
        listData = listContact
        notifyDataSetChanged()
    }

    override fun getCount(): Int = listData.size

    override fun getItem(position: Int): Any? = null

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getSwipeLayoutResourceId(position: Int): Int = R.id.contact_row_swipe_layout

    override fun generateView(position: Int, parent: ViewGroup?): View =
        LayoutInflater.from(parent?.context).inflate(R.layout.emergencycontact_row, parent, false)

    override fun fillValues(position: Int, convertView: View?) {
        val contact = listData[position]
        val tvName = convertView?.findViewById<TextView>(R.id.contact_row_name)
        val tvNumber = convertView?.findViewById<TextView>(R.id.contact_row_number)
        val tvImg = convertView?.findViewById<CircleImageView>(R.id.contact_row_img)
        val btnDelete = convertView?.findViewById<LinearLayout>(R.id.contact_row_delete)
        val btnEdit = convertView?.findViewById<LinearLayout>(R.id.contact_row_edit)

        tvName?.text = contact.name
        tvNumber?.text = contact.phoneNumber

        btnDelete?.setOnClickListener {
            onDeleteClickCallback.onDeleteContactClicked(contact, position)
        }

        btnEdit?.setOnClickListener {
            onEditClickCallback.onEditContactClicked(contact, position)
        }
    }

    interface OnDeleteClickCallback {
        fun onDeleteContactClicked(contactEntity: EmergencyContactEntity?, position: Int)
    }

    interface OnEditClickCallback {
        fun onEditContactClicked(contactEntity: EmergencyContactEntity?, position: Int)
    }

}