package pt.isec.ans.teostorage

import android.annotation.SuppressLint
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import pt.isec.ans.teostorage.databinding.ActivityMainBinding
import pt.isec.ans.teostorage.fragments.*

class StorageMainActivity : AppCompatActivity(), BaseStorageFragment.Result {
    private lateinit var b : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        val pageAdapter = StorageFragmentAdapter(this)
        b.vpStorageFragments.adapter = pageAdapter

        b.vpStorageFragments.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == 0 || pageAdapter.itemCount < 1 )
                    b.fbLeft.hide()
                else
                    b.fbLeft.show()
                if (position >=0 && position < pageAdapter.itemCount-1)
                    b.fbRight.show()
                else
                    b.fbRight.hide()
            }
        } )

        b.fbLeft.setOnClickListener {
            if (b.vpStorageFragments.currentItem > 0)
                b.vpStorageFragments.currentItem -= 1
        }
        b.fbRight.setOnClickListener {
            if (b.vpStorageFragments.currentItem < pageAdapter.itemCount-1)
                b.vpStorageFragments.currentItem += 1
        }

        if (PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) ||
            PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE))
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE),1234)
        else
            pageAdapter.unlockExtra()
    }

    //Modo tradicional de verificação das permissões
    //Nas aulas práticas serão vistas outras formas
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1234) {
            if ( (grantResults.filter { it == PERMISSION_GRANTED }.size) == grantResults.size)
                (b.vpStorageFragments.adapter as? StorageFragmentAdapter)?.unlockExtra()
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    override fun show(string: String) {
        b.textView.text = string
    }

    inner class StorageFragmentAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
        private val frags_base = arrayListOf(
            InternalStorageFragment::class.java,
            CacheFragment::class.java,
            DBFragment::class.java,
            RoomDBFragment::class.java
        )
        private val frags_ext = arrayListOf(
            InternalStorageFragment::class.java,
            CacheFragment::class.java,
            ExternalStorageFragment::class.java,
            FileDirsFragment::class.java,
            DBFragment::class.java,
            RoomDBFragment::class.java
        )
        private val frags = frags_base

        override fun getItemCount(): Int {
            return frags.size
        }

        override fun createFragment(position: Int): Fragment {
            return frags[position].newInstance()
        }

        @SuppressLint("NotifyDataSetChanged")
        fun unlockExtra() {
            frags.clear()
            frags.addAll(frags_ext)
            this.notifyDataSetChanged()
        }

    }

}