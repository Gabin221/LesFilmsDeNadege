package com.example.lesfilmsdenadge.ui.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesfilmsdenadge.CustomAdapter
import com.example.lesfilmsdenadge.FilmsViewModel
import com.example.lesfilmsdenadge.R
import com.example.lesfilmsdenadge.databinding.FragmentFilmsBinding
import com.example.lesfilmsdenadge.model.Films
import com.example.lesfilmsdenadge.ui.FilmsAdapter
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class FilmsFragment : Fragment() {

    private var _binding: FragmentFilmsBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerViewActionAventure: RecyclerView
    private lateinit var recyclerViewAnimation: RecyclerView
    private lateinit var recyclerViewBiopic: RecyclerView
    private lateinit var recyclerViewComedie: RecyclerView
    private lateinit var recyclerViewComedieDramatique: RecyclerView
    private lateinit var recyclerViewComedieMusicale: RecyclerView
    private lateinit var recyclerViewComedieRomantique: RecyclerView
    private lateinit var recyclerViewCourtMetrage: RecyclerView
    private lateinit var recyclerViewDocumentaire: RecyclerView
    private lateinit var recyclerViewDrame: RecyclerView
    private lateinit var recyclerViewFantastique: RecyclerView
    private lateinit var recyclerViewGuerre: RecyclerView
    private lateinit var recyclerViewHistorique: RecyclerView
    private lateinit var recyclerViewHorreur: RecyclerView
    private lateinit var recyclerViewRetransmission: RecyclerView
    private lateinit var recyclerViewScienceFiction: RecyclerView
    private lateinit var recyclerViewThriller: RecyclerView
    private lateinit var recyclerViewWestern: RecyclerView

    private lateinit var adapterActionAventure: FilmsAdapter<Films>
    private lateinit var adapterAnimation: FilmsAdapter<Films>
    private lateinit var adapterBiopic: FilmsAdapter<Films>
    private lateinit var adapterComedie: FilmsAdapter<Films>
    private lateinit var adapterComedieDramatique: FilmsAdapter<Films>
    private lateinit var adapterComedieMusicale: FilmsAdapter<Films>
    private lateinit var adapterComedieRomantique: FilmsAdapter<Films>
    private lateinit var adapterCourtMetrage: FilmsAdapter<Films>
    private lateinit var adapterDocumentaire: FilmsAdapter<Films>
    private lateinit var adapterDrame: FilmsAdapter<Films>
    private lateinit var adapterFantastique: FilmsAdapter<Films>
    private lateinit var adapterGuerre: FilmsAdapter<Films>
    private lateinit var adapterHistorique: FilmsAdapter<Films>
    private lateinit var adapterHorreur: FilmsAdapter<Films>
    private lateinit var adapterRetransmission: FilmsAdapter<Films>
    private lateinit var adapterScienceFiction: FilmsAdapter<Films>
    private lateinit var adapterThriller: FilmsAdapter<Films>
    private lateinit var adapterWestern: FilmsAdapter<Films>

    private lateinit var titreActionAventure: TextView
    private lateinit var titreAnimation: TextView
    private lateinit var titreBiopic: TextView
    private lateinit var titreComedie: TextView
    private lateinit var titreComedieDramatique: TextView
    private lateinit var titreComedieMusicale: TextView
    private lateinit var titreComedieRomantique: TextView
    private lateinit var titreCourtMetrage: TextView
    private lateinit var titreDocumentaire: TextView
    private lateinit var titreDrame: TextView
    private lateinit var titreFantastique: TextView
    private lateinit var titreGuerre: TextView
    private lateinit var titreHistorique: TextView
    private lateinit var titreHorreur: TextView
    private lateinit var titreRetransmission: TextView
    private lateinit var titreScienceFiction: TextView
    private lateinit var titreThriller: TextView
    private lateinit var titreWestern: TextView

    lateinit var programmingLanguagesLV: ListView
    lateinit var listAdapter: ArrayAdapter<String>
    lateinit var programmingLanguagesList: ArrayList<String>
    lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFilmsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        FirebaseApp.initializeApp(requireActivity())

        try {
            recyclerViewActionAventure = root.findViewById(R.id.recyclerViewActionAventure)
            recyclerViewActionAventure.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewAnimation = root.findViewById(R.id.recyclerViewAnimation)
            recyclerViewAnimation.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewBiopic = root.findViewById(R.id.recyclerViewBiopic)
            recyclerViewBiopic.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewComedie = root.findViewById(R.id.recyclerViewComedie)
            recyclerViewComedie.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewComedieDramatique = root.findViewById(R.id.recyclerViewComedieDramatique)
            recyclerViewComedieDramatique.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewComedieMusicale = root.findViewById(R.id.recyclerViewComedieMusicale)
            recyclerViewComedieMusicale.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewComedieRomantique = root.findViewById(R.id.recyclerViewComedieRomantique)
            recyclerViewComedieRomantique.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewCourtMetrage = root.findViewById(R.id.recyclerViewCourtMetrage)
            recyclerViewCourtMetrage.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewDocumentaire = root.findViewById(R.id.recyclerViewDocumentaire)
            recyclerViewDocumentaire.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewDrame = root.findViewById(R.id.recyclerViewDrame)
            recyclerViewDrame.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewFantastique = root.findViewById(R.id.recyclerViewFantastique)
            recyclerViewFantastique.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewGuerre = root.findViewById(R.id.recyclerViewGuerre)
            recyclerViewGuerre.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewHistorique = root.findViewById(R.id.recyclerViewHistorique)
            recyclerViewHistorique.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewHorreur = root.findViewById(R.id.recyclerViewHorreur)
            recyclerViewHorreur.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewRetransmission = root.findViewById(R.id.recyclerViewRetransmission)
            recyclerViewRetransmission.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewScienceFiction = root.findViewById(R.id.recyclerViewScienceFiction)
            recyclerViewScienceFiction.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewThriller = root.findViewById(R.id.recyclerViewThriller)
            recyclerViewThriller.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewWestern = root.findViewById(R.id.recyclerViewWestern)
            recyclerViewWestern.layoutManager = LinearLayoutManager(requireContext())

            titreActionAventure = root.findViewById(R.id.titreActionAventure)
            titreAnimation = root.findViewById(R.id.titreAnimation)
            titreBiopic = root.findViewById(R.id.titreBiopic)
            titreComedie = root.findViewById(R.id.titreComedie)
            titreComedieDramatique = root.findViewById(R.id.titreComedieDramatique)
            titreComedieMusicale = root.findViewById(R.id.titreComedieMusicale)
            titreComedieRomantique = root.findViewById(R.id.titreComedieRomantique)
            titreCourtMetrage = root.findViewById(R.id.titreCourtMetrage)
            titreDocumentaire = root.findViewById(R.id.titreDocumentaire)
            titreDrame = root.findViewById(R.id.titreDrame)
            titreFantastique = root.findViewById(R.id.titreFantastique)
            titreGuerre = root.findViewById(R.id.titreGuerre)
            titreHistorique = root.findViewById(R.id.titreHistorique)
            titreHorreur = root.findViewById(R.id.titreHorreur)
            titreRetransmission = root.findViewById(R.id.titreRetransmission)
            titreScienceFiction = root.findViewById(R.id.titreScienceFiction)
            titreThriller = root.findViewById(R.id.titreThriller)
            titreWestern = root.findViewById(R.id.titreWestern)

            adapterActionAventure = FilmsAdapter(emptyList()) {}
            adapterAnimation = FilmsAdapter(emptyList()) {}
            adapterBiopic = FilmsAdapter(emptyList()) {}
            adapterComedie = FilmsAdapter(emptyList()) {}
            adapterComedieDramatique = FilmsAdapter(emptyList()) {}
            adapterComedieMusicale = FilmsAdapter(emptyList()) {}
            adapterComedieRomantique = FilmsAdapter(emptyList()) {}
            adapterCourtMetrage = FilmsAdapter(emptyList()) {}
            adapterDocumentaire = FilmsAdapter(emptyList()) {}
            adapterDrame = FilmsAdapter(emptyList()) {}
            adapterFantastique = FilmsAdapter(emptyList()) {}
            adapterGuerre = FilmsAdapter(emptyList()) {}
            adapterHistorique = FilmsAdapter(emptyList()) {}
            adapterHorreur = FilmsAdapter(emptyList()) {}
            adapterRetransmission = FilmsAdapter(emptyList()) {}
            adapterScienceFiction = FilmsAdapter(emptyList()) {}
            adapterThriller = FilmsAdapter(emptyList()) {}
            adapterWestern = FilmsAdapter(emptyList()) {}

            recyclerViewActionAventure.adapter = adapterActionAventure
            recyclerViewAnimation.adapter = adapterAnimation
            recyclerViewBiopic.adapter = adapterBiopic
            recyclerViewComedie.adapter = adapterComedie
            recyclerViewComedieDramatique.adapter = adapterComedieDramatique
            recyclerViewComedieMusicale.adapter = adapterComedieMusicale
            recyclerViewComedieRomantique.adapter = adapterComedieRomantique
            recyclerViewCourtMetrage.adapter = adapterCourtMetrage
            recyclerViewDocumentaire.adapter = adapterDocumentaire
            recyclerViewDrame.adapter = adapterDrame
            recyclerViewFantastique.adapter = adapterFantastique
            recyclerViewGuerre.adapter = adapterGuerre
            recyclerViewHistorique.adapter = adapterHistorique
            recyclerViewHorreur.adapter = adapterHorreur
            recyclerViewRetransmission.adapter = adapterRetransmission
            recyclerViewScienceFiction.adapter = adapterScienceFiction
            recyclerViewThriller.adapter = adapterThriller
            recyclerViewWestern.adapter = adapterWestern

            programmingLanguagesLV = root.findViewById(R.id.idLVProgrammingLanguages)
            searchView = root.findViewById(R.id.idSV)

            programmingLanguagesList = ArrayList()

            chargerFilms()

            val listeFilmsFiltree = mutableListOf<String>()

            listAdapter = ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                listeFilmsFiltree
            )
            programmingLanguagesLV.adapter = listAdapter

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    listeFilmsFiltree.clear()
                    if (newText != null && newText.isNotEmpty()) {
                        for (film in programmingLanguagesList) {
                            if (film.contains(newText, ignoreCase = true)) {
                                listeFilmsFiltree.add(film)
                            }
                        }
                        programmingLanguagesLV.visibility = if (listeFilmsFiltree.isEmpty()) GONE else VISIBLE
                    } else {
                        programmingLanguagesLV.visibility = GONE
                    }
                    listAdapter.notifyDataSetChanged()
                    return false
                }
            })
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Erreur lors de la récupération des données Firestore: ${e.message}", Toast.LENGTH_SHORT).show()
        }
        return root
    }

    private fun chargerFilms() {
        val db = FirebaseFirestore.getInstance()
        val genres = listOf(
            "Action-Aventure", "Animation", "Biopic", "Comédie", "Comédie dramatique", "Comédie musicale",
            "Comédie romantique", "Court métrage", "Documentaire", "Drame", "Fantastique", "Guerre", "Historique",
            "Horreur", "Retransmission", "Science-fiction", "Thriller", "Western"
        )

        for (genre in genres) {
            val collection = db.collection("Films").document("Style").collection(genre)
            collection.get().addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val data = ArrayList<FilmsViewModel>()
                    for (document in documents) {
                        val nom = document.getString("titre") ?: ""
                        val realisateur = document.getString("realisateur") ?: ""
                        val dateSortie = document.getString("sortie") ?: ""
                        val steelbook = document.getString("steelbook") ?: ""
                        val image = document.getString("image") ?: ""
                        val element = FilmsViewModel(image, nom, realisateur, dateSortie, steelbook)
                        if (steelbook == "true") {
                            programmingLanguagesList.add("${element.titre} (${element.realisateur}) sorti le ${element.sortie} en steelbook")
                        } else {
                            programmingLanguagesList.add("${element.titre} (${element.realisateur}) sorti le ${element.sortie}")
                        }
                        data.add(element)
                    }
                    val adapter = CustomAdapter(data)

                    when (genre) {
                        "Action-Aventure" -> recyclerViewActionAventure.adapter = adapter
                        "Animation" -> recyclerViewAnimation.adapter = adapter
                        "Biopic" -> recyclerViewBiopic.adapter = adapter
                        "Comédie" -> recyclerViewComedie.adapter = adapter
                        "Comédie dramatique" -> recyclerViewComedieDramatique.adapter = adapter
                        "Comédie musicale" -> recyclerViewComedieMusicale.adapter = adapter
                        "Comédie romantique" -> recyclerViewComedieRomantique.adapter = adapter
                        "Court métrage" -> recyclerViewCourtMetrage.adapter = adapter
                        "Documentaire" -> recyclerViewDocumentaire.adapter = adapter
                        "Drame" -> recyclerViewDrame.adapter = adapter
                        "Fantastique" -> recyclerViewFantastique.adapter = adapter
                        "Guerre" -> recyclerViewGuerre.adapter = adapter
                        "Historique" -> recyclerViewHistorique.adapter = adapter
                        "Horreur" -> recyclerViewHorreur.adapter = adapter
                        "Retransmission" -> recyclerViewRetransmission.adapter = adapter
                        "Science-fiction" -> recyclerViewScienceFiction.adapter = adapter
                        "Thriller" -> recyclerViewThriller.adapter = adapter
                        "Western" -> recyclerViewWestern.adapter = adapter
                    }
                    affichageTitres(genre)
                }
            }.addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Erreur lors du chargement des films de la catégorie $genre : $e", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun affichageTitres(input: String) {
        when (input) {
            "Action-Aventure" -> titreActionAventure.visibility = VISIBLE
            "Animation" -> titreAnimation.visibility = VISIBLE
            "Biopic" -> titreBiopic.visibility = VISIBLE
            "Comédie" -> titreComedie.visibility = VISIBLE
            "Comédie dramatique" -> titreComedieDramatique.visibility = VISIBLE
            "Comédie musicale" -> titreComedieMusicale.visibility = VISIBLE
            "Comédie romantique" -> titreComedieRomantique.visibility = VISIBLE
            "Court métrage" -> titreCourtMetrage.visibility = VISIBLE
            "Documentaire" -> titreDocumentaire.visibility = VISIBLE
            "Drame" -> titreDrame.visibility = VISIBLE
            "Fantastique" -> titreFantastique.visibility = VISIBLE
            "Guerre" -> titreGuerre.visibility = VISIBLE
            "Historique" -> titreHistorique.visibility = VISIBLE
            "Horreur" -> titreHorreur.visibility = VISIBLE
            "Retransmission" -> titreRetransmission.visibility = VISIBLE
            "Science-fiction" -> titreScienceFiction.visibility = VISIBLE
            "Thriller" -> titreThriller.visibility = VISIBLE
            "Western" -> titreWestern.visibility = VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
