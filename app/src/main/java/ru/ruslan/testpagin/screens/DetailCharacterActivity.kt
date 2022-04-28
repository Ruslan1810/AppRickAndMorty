package ru.ruslan.testpagin.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_character.*
import ru.ruslan.testpagin.databinding.ActivityDetailCharacterBinding
import ru.ruslan.testpagin.network.api.model.CharacterData

class DetailCharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCharacterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.extras
        val character = data?.getParcelable<CharacterData>("CHARACTER")

        binding.tvName.text = character?.name
        "Species - ${character?.species}".also { binding.tvSpecies.text = it }
        "Gender - ${character?.gender}".also { binding.tvGender.text = it }
        "Status - ${character?.status}".also { binding.tvStatus.text = it }
        "Number of episodes - ${character?.episode?.size}".also { binding.tvCountEpisode.text = it }
        "Last location - ${character?.location?.name}".also { binding.tvLocation.text = it }
        Picasso.get().load(character?.image).into(imageView)
    }
}