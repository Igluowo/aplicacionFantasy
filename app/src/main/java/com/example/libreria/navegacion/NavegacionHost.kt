package com.example.libreria.navegacion

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.libreria.clases.Tarjeta
import com.example.libreria.pantallas.PantallaPrincipal
import com.example.libreria.pantallas.PantallaFantasy
import com.example.libreria.pantallas.PantallaAniadir

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavegacionHost(context : Context, volver : Boolean) {
    val navController = rememberNavController()
    var tarjetas = ArrayList<Tarjeta>()
    var id by remember { mutableIntStateOf(0) }
    NavHost(navController = navController, startDestination = Navegacion.PantallaPrincipal.ruta ) {
        composable(route = Navegacion.PantallaPrincipal.ruta) {
            PantallaPrincipal(navController)
        }
        composable(route = Navegacion.PantallaFantasy.ruta) {
            PantallaFantasy(navController, context, tarjetas, id)
        }
        composable(route = Navegacion.PantallaAniadir.ruta) {
            PantallaAniadir(navController, context, tarjetas, id)
            id++
        }
        composable(route = Navegacion.PantallaDetalles.ruta) {

        }
    }
}