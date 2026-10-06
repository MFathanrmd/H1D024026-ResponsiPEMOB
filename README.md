# PokéDex App
> Aplikasi Android sederhana buat nge-list Pokémon dari PokéAPI. Dibikin buat tugas Responsi Pemob.

---

## 👤 Identitas Praktikan
- **Nama Lengkap:** Muhammad Fathan Ramdani
- **NIM:** H1D024026
- **Shift Awal:** Shift G
- **Shift Akhir:** Shift C
- **Link Video Demo/Penjelasan:** [YouTube Demo](https://youtu.be/placeholder)

---

## 📱 Deskripsi Aplikasi
Aplikasi **PokéDex** ini intinya buat ngeliat daftar Pokémon sama detail-detailnya. Datanya ngambil langsung (live) dari API publik [PokéAPI](https://pokeapi.co). 
Pas awal buka, kita bisa langsung nyari nama Pokémon atau scroll aja ke bawah karena udah ada fitur infinite scroll-nya, jadi datanya bakal ke-load terus. Terus kalau salah satu Pokémon diklik, bakal masuk ke halaman detail yang nampilin gambar, elemen/tipe, status, tinggi, berat, sama ability-nya. Di pojok atas juga ada tombol buat masuk ke halaman profil mahasiswa.

---

## 🛠️ Penjelasan Teknis

### 1. Spesifikasi & Tech Stack
- **Bahasa:** Kotlin (versi 2.0.21)
- **UI Framework:** Jetpack Compose (pake Material 3)
- **Arsitektur:** MVVM (Model-View-ViewModel)
- **Library yang dipake:**
  - `Navigation Compose` -> Buat pindah-pindah halaman
  - `Retrofit` & `OkHttp` -> Buat nembak API dan ngambil data JSON
  - `Coil` -> Buat nge-load gambar dari link URL
  - `Coroutines` -> Biar proses ambil data jalan di background tanpa bikin UI ngelag
  - `Gson` -> Buat parsing JSON ke data class Kotlin

### 2. Fitur Utama
- **Home Screen:** Nampilin list Pokémon pake format Grid. Bisa discroll terus ke bawah (lazy load) dan ada kolom pencariannya.
- **Detail Screen:** Halaman info lengkap Pokémon. Nampilin info base stats, ability, dan warna UI-nya berubah otomatis menyesuaikan tipe elemen Pokémon-nya.
- **About Screen:** Halaman biodata/profil sama info singkat soal aplikasi.

### 3. Struktur Direktori Proyek
Biar kodenya rapi, strukturnya dibagi kayak gini:
```text
app/src/main/java/com/example/pokemonapp/
├── data/           # Isinya model data, setup Retrofit, sama Repository API
├── ui/
│   ├── navigation/ # Buat ngatur alur pindah halaman
│   ├── screens/    # File UI tiap halaman (Home, Detail, About) beserta ViewModel-nya
│   └── theme/      # Aturan warna dan font buat tema aplikasinya
└── MainActivity.kt
```

---

## 📸 Tangkapan Layar (Screenshots)

| Home Screen | Detail Screen | About Screen |
|:---:|:---:|:---:|
| ![Splash/Home](docs/screen1.jpg) | ![List/Detail](docs/screen2.jpg) | ![Form/Settings](docs/screen3.jpg) |

---

## 🚀 Cara Menjalankan Proyek

1. **Syaratnya:**
   - Laptop udah keinstall Android Studio.
   - Harus ada koneksi internet pas running aplikasinya (buat nembak API & load gambar).
2. **Cara jalaninnya:**
   - Buka terminal, ketik: `git clone https://github.com/MFathanrmd/H1D024026-ResponsiPEMOB.git`
   - Buka foldernya di **Android Studio**.
   - Tunggu proses Gradle Sync sampe kelar.
   - Pilih emulator atau device, terus tinggal **Run (`Shift + F10`)**.
