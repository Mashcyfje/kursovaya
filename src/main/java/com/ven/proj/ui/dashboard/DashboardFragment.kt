package com.ven.proj.ui.dashboard


import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ven.proj.R
import com.ven.proj.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
//    private val binding get() = _binding!!

    private var buttonPlayStop: Button? = null
    private var mediaPlayer: MediaPlayer? = null
    private var seekBar: SeekBar? = null
    private var textView1: TextView? = null
    private var textView2: TextView? = null
    private var textView3: TextView? = null
    private val finalTime = 0.0
    private var totalTime: Int = 0
    private val finalTime2 = 0.0


    @Override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {


        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = _binding!!.root
        //final MediaPlayer mediaPlayer = MediaPlayer.create(binding.this, R.raw.mymusic);
        //MediaPlayer mediaPlayer = MediaPlayer.create(binding.this, R.raw.mymusic);
        //
            //_binding!!.textView4.setText("            android:text=\" \\nПолное расслабление.  \\nМедленно вдохните через нос за 4 секунды;              \\nЗадержите дыхание на 4 секунды;              \\nВыдыхайте 4 секунды через сжатые губы, словно играете на флейте.     \\nРасслабляйте мышцы лица, челюсти, горла и плеч.\"\n")
        var mediaPlayer = MediaPlayer.create(context, com.ven.proj.R.raw.da)
        mediaPlayer.isLooping = true
        seekBar =  _binding!!.seekBar as SeekBar
        val totalTime = mediaPlayer.duration
        seekBar!!.max = totalTime
        seekBar!!.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        mediaPlayer.seekTo(progress)
                    }
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {
                }
                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            }
        )
        Thread(Runnable {
            while (mediaPlayer != null) {
                try {
                    var msg = Message()
                    msg.what = mediaPlayer.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                }
            }
        }).start()
        val play = _binding!!.play
       // val pause = _binding!!.pause


        //play.setOnClickListener{playBtnClick(binding.play)}
        //{
           // playBtnClick(binding.play)
           /// mediaPlayer.start()

       // }//(onTabClick)
        play.setOnClickListener{
            if (mediaPlayer!!.isPlaying) {
            // Stop
            mediaPlayer!!.pause()
                _binding!!.play.setBackgroundResource(R.drawable.play)

        } else {
            // Start
            mediaPlayer!!.start()
                _binding!!.play.setBackgroundResource(R.drawable.stop)
        }}
        //pause.setOnClickListener { mediaPlayer.pause() }

        //val textView: TextView = binding.textDashboard
        //dashboardViewModel.text.observe(viewLifecycleOwner) {
         //   textView.text = it
        //}

        return root
    }
    @SuppressLint("HandlerLeak")
    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            var currentPosition = msg.what

            // Update positionBar
            seekBar!!.progress = currentPosition

            // Update Labels
          //  var elapsedTime = createTimeLabel(currentPosition)
          //  _binding!!.elapsedTimeLabel.text = elapsedTime

         //   var remainingTime = createTimeLabel(totalTime - currentPosition)
          //  _binding!!.remainingTimeLabel.text = "-$remainingTime"
        }
    }
    fun createTimeLabel(time: Int): String {
        var timeLabel = ""
        var min = time / 1000 / 60
        var sec = time / 1000 % 60

        timeLabel = "$min:"
        if (sec < 10) timeLabel += "0"
        timeLabel += sec

        return timeLabel
    }
    fun playBtnClick(v: View) {

        if (mediaPlayer!!.isPlaying) {
            // Stop
            mediaPlayer!!.pause()
            _binding!!.play.setBackgroundResource(R.drawable.play)

        } else {
            // Start
            mediaPlayer!!.start()
            _binding!!.play.setBackgroundResource(R.drawable.stop)
        }
    }
  /*  private fun initViews() {
        buttonPlayStop = binding.play
        mediaPlayer = MediaPlayer.create(context, com.ven.proj.R.raw.da)
        seekBar =  binding.seekBar as SeekBar

        seekBar!!.setMax(mediaPlayer!!.getCurrentPosition())
        seekBar!!.setOnTouchListener { v, event ->
            seekChange(v)
            false
        }
        textView1 = findViewById(R.id.textView1) as TextView
        textView2 = findViewById(R.id.textView2) as TextView
        textView3 = findViewById(R.id.textView3) as TextView
    }*/
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}