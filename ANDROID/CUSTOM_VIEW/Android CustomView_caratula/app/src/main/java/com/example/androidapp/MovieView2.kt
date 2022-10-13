package com.example.androidapp

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class MovieView2 @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr:Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    init {

    }


}

//necesitamos hacer que todos los constructores funcionen en java, que es lo que ejecutará nuestro programa.
//para poder usarse en java, tendremos que marcar cada constructor con la anotación @JvmOverloads
//le decimos a java que cuando genere esta clase, genere también todas las posibles sobrecargas de este constructor
//así a nivel de bytecode, tendremos las mismas oportunidades con java de usar los distintos constructores