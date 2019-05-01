package diegowendel.github.io.cyberkids.utils

import android.content.Context

class PreferenceUtils {

    companion object {
        private val PREF_ID = "cyberkids"

        fun setBoolean(context: Context, flag: String, on: Boolean) {
            val pref = context.getSharedPreferences(PREF_ID, 0)
            val editor = pref.edit()
            editor.putBoolean(flag, on)
            editor.commit()
        }

        fun getBoolean(context: Context, flag: String): Boolean {
            val pref = context.getSharedPreferences(PREF_ID, 0)
            return pref.getBoolean(flag, false)
        }

        fun setInteger(context: Context, flag: String, valor: Int) {
            val pref = context.getSharedPreferences(PREF_ID, 0)
            val editor = pref.edit()
            editor.putInt(flag, valor)
            editor.commit()
        }

        fun getInteger(context: Context, flag: String): Int {
            val pref = context.getSharedPreferences(PREF_ID, 0)
            return pref.getInt(flag, 0)
        }

        fun setString(context: Context, flag: String, valor: String) {
            val pref = context.getSharedPreferences(PREF_ID, 0)
            val editor = pref.edit()
            editor.putString(flag, valor)
            editor.commit()
        }

        fun getString(context: Context, flag: String): String? {
            val pref = context.getSharedPreferences(PREF_ID, 0)
            return pref.getString(flag, null)
        }

        fun clearPreferences(context: Context) {
            val pref = context.getSharedPreferences(PREF_ID, 0)
            val editor = pref.edit()
            editor.clear().commit()
        }
    }
}