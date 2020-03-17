package com.example.raportybhp.test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.raportybhp.R
import com.example.raportybhp.addProject.projectsDTB
import com.example.raportybhp.projects.Projects
import kotlinx.android.synthetic.main.projects_list.view.*

class ProjectAdapter(val mCtx: Context, val layoutResID: Int, val projectList: List<projectsDTB>)
    : ArrayAdapter<projectsDTB>(mCtx, layoutResID, projectList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater = LayoutInflater.from(mCtx)
        val view = layoutInflater.inflate(layoutResID, null)
        val textViewName = view.findViewById<TextView>(R.id.tvListName)
        val textTAGname = view.findViewById<TextView>(R.id.tvTAGname)

        val project = projectList[position]

        textViewName.text = project.nameGW
        textTAGname.text = project.name

        return view
    }
}