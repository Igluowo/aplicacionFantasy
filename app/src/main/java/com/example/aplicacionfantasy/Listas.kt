package com.example.aplicacionfantasy

class Listas {
    var listaPaises = listOf(
        "Brasil",
        "Estados Unidos",
        "Paises Bajos",
        "Singapur",
        "Reino Unido",
        "Alemania",
        "Corea del Sur",
        "Japon",
        "Chile",
        "Turquia",
        "China",
        "Turquia",
        "España",
        "Indonesia",
        "Rusia",
        "Tailandia"
    )

    val listaEquipos = listOf(
        Equipo(R.drawable.cloud9, "Cloud9"),
        Equipo(R.drawable.cloud9, "Loud"),
        Equipo(R.drawable.evil, "Evil Geniuses"),
        Equipo(R.drawable.paperrex, "Paper Rex"),
        Equipo(R.drawable.fnatic, "Fnatic"),
        Equipo(R.drawable.optic, "Optic Gaming"),
        Equipo(R.drawable.drx, "DRX"),
        Equipo(R.drawable.funplus, "FunPlus Fenix"),
        Equipo(R.drawable.zeta, "ZETA DIVISION"),
        Equipo(R.drawable.g2, "G2 Esports"),
        Equipo(R.drawable.teamliquid, "Team Liquid"),
        Equipo(R.drawable.krus, "KRU Esports"),
        Equipo(R.drawable.edward, "EDward Gaming"),
        Equipo(R.drawable.xerxia, "Xerxia"),
        Equipo(R.drawable.t1, "T1"),
        Equipo(R.drawable.xset, "XSET"),
        Equipo(R.drawable.nrg, "NRG"),
        Equipo(R.drawable.navi, "NAVI"),
        Equipo(R.drawable.guard, "The Guard"),
        Equipo(R.drawable.leviatan, "Leviatan Esports"),
        Equipo(R.drawable.hundredthieves, "100 Thieves"),
        Equipo(R.drawable.giant, "Giant Gaming"),
        Equipo(R.drawable.bleed, "Bleed Esports"),
        Equipo(R.drawable.furia, "FURIA Esports"),
        Equipo(R.drawable.bilibili, "Bilibili Gaming"),
        Equipo(R.drawable.boom, "BOOM Esports")
    )

    val listaEquiposNombre = listOf(
        "Loud",
        "Evil Geniuses",
        "Paper Rex",
        "Fnatic",
        "Optic Gaming",
        "DRX",
        "FunPlus Fenix",
        "ZETA DIVISION",
        "G2 Esports",
        "Team Liquid",
        "KRU Esports",
        "EDward Gaming",
        "Xerxia",
        "T1",
        "XSET",
        "NRG",
        "Shopify Rebellion GC",
        "NAVI",
        "The Guard",
        "Leviatan Esports",
        "100 Thieves",
        "Cloud9",
        "Giant Gaming",
        "Bleed Esport",
        "FURIA Esports",
        "Bilibili Gaming",
        "BOOM Esports"
    )
}

data class Equipo(val imagen: Int, val nombre: String)