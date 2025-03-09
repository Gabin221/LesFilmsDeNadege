package com.example.lesfilmsdenadge.ui.ajouter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import com.example.lesfilmsdenadge.FilmsViewModel
import com.example.lesfilmsdenadge.R
import com.example.lesfilmsdenadge.databinding.FragmentAjouterBinding
import com.google.firebase.firestore.FirebaseFirestore

class AjouterFragment : Fragment() {
    private var _binding: FragmentAjouterBinding? = null
    private val binding get() = _binding!!

    lateinit var programmingLanguagesLV: ListView
    lateinit var searchView: SearchView
    lateinit var programmingLanguagesList: ArrayList<Pair<String, String>> // Pair of film title and category
    lateinit var listAdapter: ArrayAdapter<Pair<String, String>>
    lateinit var editTextTitle: EditText
    lateinit var editTextDirector: EditText
    lateinit var editTextReleaseDate: EditText
    lateinit var spinnerSteelbook: Spinner
    lateinit var editTextImage: EditText
    lateinit var spinnerCategory: Spinner
    lateinit var buttonAddFilm: Button

    private val categories = listOf(
        "Action-Aventure", "Animation", "Biopic", "Comédie", "Comédie dramatique", "Comédie musicale",
        "Comédie romantique", "Court métrage", "Documentaire", "Drame", "Fantastique", "Guerre", "Historique",
        "Horreur", "Retransmission", "Science-fiction", "Thriller", "Western"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAjouterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        programmingLanguagesLV = root.findViewById(R.id.idLVProgrammingLanguages)
        searchView = root.findViewById(R.id.idSV)
        editTextTitle = root.findViewById(R.id.editTextTitle)
        editTextDirector = root.findViewById(R.id.editTextDirector)
        editTextReleaseDate = root.findViewById(R.id.editTextReleaseDate)
        spinnerSteelbook = root.findViewById(R.id.spinnerSteelbook)
        editTextImage = root.findViewById(R.id.editTextImage)
        spinnerCategory = root.findViewById(R.id.spinnerCategory)
        buttonAddFilm = root.findViewById(R.id.buttonAddFilm)

        programmingLanguagesList = ArrayList()
        val listeFilmsFiltree = mutableListOf<Pair<String, String>>()

        chargerFilms()

        listAdapter = object : ArrayAdapter<Pair<String, String>>(requireContext(), 0, listeFilmsFiltree) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_film, parent, false)
                val filmTitle = view.findViewById<TextView>(R.id.tvFilmTitle)
                val filmDetails = view.findViewById<TextView>(R.id.tvFilmDetails)
                val film = getItem(position)
                if (film != null) {
                    filmTitle.text = film.first
                    filmDetails.text = "Catégorie: ${film.second}"
                }
                return view
            }
        }

        programmingLanguagesLV.adapter = listAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                listeFilmsFiltree.clear()
                if (newText != null && newText.isNotEmpty()) {
                    for (film in programmingLanguagesList) {
                        if (film.first.contains(newText, ignoreCase = true)) {
                            listeFilmsFiltree.add(film)
                        }
                    }
                    programmingLanguagesLV.visibility = if (listeFilmsFiltree.isEmpty()) View.GONE else View.VISIBLE
                    setListViewHeightBasedOnChildren(programmingLanguagesLV)
                } else {
                    programmingLanguagesLV.visibility = View.GONE
                }
                listAdapter.notifyDataSetChanged()
                return false
            }
        })

        programmingLanguagesLV.setOnItemClickListener { _, _, position, _ ->
            val selectedFilm = listeFilmsFiltree[position]
            afficherConfirmationSuppression(selectedFilm.first, selectedFilm.second)
        }

        buttonAddFilm.setOnClickListener {
            ajouterFilm()
        }

        // Initialiser le Spinner pour les catégories
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = spinnerAdapter

        val spinnerAdapterSteelbook = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listOf("false", "true"))
        spinnerAdapterSteelbook.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSteelbook.adapter = spinnerAdapterSteelbook

        return root
    }

    private fun setListViewHeightBasedOnChildren(listView: ListView) {
        val listAdapter = listView.adapter ?: return

        var totalHeight = 0
        for (i in 0 until listAdapter.count) {
            val listItem = listAdapter.getView(i, null, listView)
            listItem.measure(
                View.MeasureSpec.makeMeasureSpec(listView.width, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.UNSPECIFIED
            )
            totalHeight += listItem.measuredHeight
        }

        val params = listView.layoutParams
        params.height = totalHeight + (listView.dividerHeight * (listAdapter.count - 1))
        listView.layoutParams = params
    }

    private fun chargerFilms() {
        val db = FirebaseFirestore.getInstance()

        for (genre in categories) {
            val collection = db.collection("Films").document("Style").collection(genre)
            collection.get().addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    for (document in documents) {
                        val nom = document.getString("titre") ?: ""
                        val realisateur = document.getString("realisateur") ?: ""
                        val dateSortie = document.getString("sortie") ?: ""
                        val steelbook = document.getString("steelbook") ?: ""
                        val image = document.getString("image") ?: ""
                        val element = FilmsViewModel(image, nom, realisateur, dateSortie, steelbook)
                        val displayString = if (steelbook == "true") {
                            "${element.titre}"
                        } else {
                            "${element.titre}"
                        }
                        programmingLanguagesList.add(Pair(displayString, genre))
                    }
                }
            }.addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Erreur lors du chargement des films de la catégorie $genre : $e", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun ajouterFilm() {
        val title = editTextTitle.text.toString()
        val director = editTextDirector.text.toString()
        val releaseDate = editTextReleaseDate.text.toString()
        val steelbook = spinnerSteelbook.selectedItem.toString()
        val image = editTextImage.text.toString()
        val category = spinnerCategory.selectedItem.toString()

        val db = FirebaseFirestore.getInstance()
        val film = FilmsViewModel(image, title, director, releaseDate, steelbook)
        db.collection("Films").document("Style").collection(category)
            .add(film)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Film ajouté avec succès", Toast.LENGTH_SHORT).show()
                val displayString = if (film.steelbook == "true") {
                    "${film.titre}"
                } else {
                    "${film.titre}"
                }
                programmingLanguagesList.add(Pair(displayString, category))
                listAdapter.notifyDataSetChanged()

                editTextTitle.getText().clear()
                editTextDirector.getText().clear()
                editTextReleaseDate.getText().clear()
                spinnerSteelbook.setSelection(0)
                editTextImage.getText().clear()
                spinnerCategory.setSelection(0)
            }.addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Erreur lors de l'ajout du film : $e", Toast.LENGTH_SHORT).show()
            }
    }

    private fun supprimerFilm(title: String, category: String) {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection("Films").document("Style").collection(category)
        collection.whereEqualTo("titre", title).get().addOnSuccessListener { documents ->
            for (document in documents) {
                collection.document(document.id).delete().addOnSuccessListener {
                    Toast.makeText(requireContext(), "Film supprimé avec succès", Toast.LENGTH_SHORT).show()
                    programmingLanguagesList.removeIf { it.first.contains(title) && it.second == category }
                    listAdapter.notifyDataSetChanged()
                    searchView.setQuery("", false)
                }.addOnFailureListener { e ->
                    Toast.makeText(requireContext(), "Erreur lors de la suppression du film : $e", Toast.LENGTH_SHORT).show()
                }
            }
        }.addOnFailureListener { e ->
            Toast.makeText(requireContext(), "Erreur lors de la recherche du film à supprimer : $e", Toast.LENGTH_SHORT).show()
        }
    }

    private fun afficherConfirmationSuppression(title: String, category: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirmation")
        builder.setMessage("Êtes-vous sûr de vouloir supprimer le film '$title' de la catégorie '$category' ?")
        builder.setPositiveButton("Oui") { dialog, _ ->
            supprimerFilm(title, category)
            dialog.dismiss()
        }
        builder.setNegativeButton("Non") { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
