package com.example.jetpackcomposetutorial
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposetutorial.ui.theme.JetpackComposeTutorialTheme

private val msjes: List<MiMensaje> = listOf(
    MiMensaje("Hola JetPack Compose","Estas preparado para este framework de creación de vistas potentisimo, habla p ctv?"),
    MiMensaje("Hola JetPack Compose 2","Estas preparado para este framework de creación de vistas potentisimo, habla p ctv? 2"),
    MiMensaje("Hola JetPack Compose 3","Estas preparado para este framework de creación de vistas potentisimo, habla p ctv? 3"),
    MiMensaje("Hola JetPack Compose 4","Estas preparado para este framework de creación de vistas potentisimo, habla p ctv? 4"),
    MiMensaje("Hola JetPack Compose 5","Estas preparado para este framework de creación de vistas potentisimo, habla p ctv? 5"),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //El tema de la app por defecto se crea en /ui.theme/Theme.kt
            JetpackComposeTutorialTheme() {
                MisMensajes(msjes)
            }
        }
    }
}

data class MiMensaje(val titulo: String, val body: String)

@Composable
fun MisMensajes(mensajes: List<MiMensaje>){
    LazyColumn{
        items(mensajes){ msj ->
            MisComponentes(msj)
        }
    }
}

@Composable
fun MisComponentes(mensaje: MiMensaje){
    //Row sirve para colocar elementos consecutivos uno alado de otro.
    Row(modifier = Modifier
        .background(MaterialTheme.colors.background)
        .padding(8.dp)) {
        myImage()
        MyTexts(mensaje)
    }
}

@Composable
fun myImage(){
    Image(
        painterResource(R.drawable.ic_launcher_foreground),
        "Imagen de prueba",
        modifier = Modifier
            .clip(CircleShape)
            .background(color = androidx.compose.ui.graphics.Color.Green)
            .size(64.dp)
    )
}


@Composable
fun MyTexts(mensaje: MiMensaje){

    //by remember mutablestateof: Almacenar y modificar valores en tiempo de ejecucion
    var expandida by remember { mutableStateOf(false)  }
    //Column  sirve para poner elementos uno debajo de otro
    Column(modifier = Modifier
        .padding(start = 8.dp)
        .clickable {
            expandida = !expandida //al hacer click vemos si esta expandida para ver todo el texto
        }){
        MyText(mensaje.titulo
            , MaterialTheme.colors.primary
            , MaterialTheme.typography.subtitle1
            , if(expandida) Int.MAX_VALUE else 1)
        Spacer(modifier = Modifier.height(16.dp))
        MyText(mensaje.body
            , MaterialTheme.colors.onBackground
            , MaterialTheme.typography.subtitle2
            ,if(expandida) Int.MAX_VALUE else 1)
    }
}

@Composable //Indicamos que sera un elemento gráfico.
fun MyText(texto: String, elcolor: Color, estilo: TextStyle, lineas: Int = Int.MAX_VALUE){
    Text(texto, color = elcolor, style = estilo, maxLines = lineas)
}

//Una preview sirve para visualizar en "split" sin recargar el emulador.
@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewComponentes(){
    JetpackComposeTutorialTheme {
            MisMensajes(msjes)
        }
}
