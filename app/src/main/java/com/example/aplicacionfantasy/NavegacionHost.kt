package com.example.aplicacionfantasy

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplicacionfantasy.pantallas.PantallaPrincipal
import com.example.aplicacionfantasy.pantallas.PantallaFantasy
import com.example.aplicacionfantasy.pantallas.PantallaAniadir

@Composable
fun NavegacionHost(context : Context, volver : Boolean) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navegacion.PantallaPrincipal.ruta ) {
        composable(route = Navegacion.PantallaPrincipal.ruta) {
            PantallaPrincipal(navController)
        }
        composable(route = Navegacion.PantallaFantasy.ruta) {
            PantallaFantasy(navController, context)
        }
        composable(route = Navegacion.PantallaAniadir.ruta) {
            PantallaAniadir(navController, context)
        }
    }
}