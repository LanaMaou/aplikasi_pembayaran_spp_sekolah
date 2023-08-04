-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 04 Agu 2023 pada 09.40
-- Versi server: 10.4.24-MariaDB
-- Versi PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_spp`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `cek_pembayaran`
--

CREATE TABLE `cek_pembayaran` (
  `nisn` varchar(10) NOT NULL,
  `tgl_terakhir_bayar` date NOT NULL,
  `tgl_sekarang` date NOT NULL,
  `status_pembayaran` enum('Belum Lunas','Sudah Lunas') NOT NULL,
  `jumlah_bulan` varchar(5) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `no_telp` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `cek_pembayaran`
--

INSERT INTO `cek_pembayaran` (`nisn`, `tgl_terakhir_bayar`, `tgl_sekarang`, `status_pembayaran`, `jumlah_bulan`, `nama`, `no_telp`) VALUES
('660001', '2023-07-01', '2023-05-01', 'Sudah Lunas', '2', 'Ahmad Royhan Azmi', '082934724354'),
('660002', '2023-06-01', '2023-05-01', 'Sudah Lunas', '1', 'Andrean', '081237124453'),
('660003', '2023-06-01', '2023-05-01', 'Sudah Lunas', '1', 'M. Perdi Fratama', '083142106988'),
('660004', '2023-04-01', '2023-05-01', 'Belum Lunas', '1', 'Ahmad Maulana Alaudin', '081968234872'),
('660005', '2023-04-01', '2023-05-01', 'Belum Lunas', '1', 'Imam Zikri Maulidi', '088273482534');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_kelas`
--

CREATE TABLE `tb_kelas` (
  `id_kelas` varchar(11) NOT NULL,
  `nama_kelas` varchar(10) NOT NULL,
  `komp_keahlian` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_kelas`
--

INSERT INTO `tb_kelas` (`id_kelas`, `nama_kelas`, `komp_keahlian`) VALUES
('KLS-0001', 'X', 'Rekayasa Perangkat Lunak'),
('KLS-0002', 'XI', 'Rekayasa Perangkat Lunak'),
('KLS-0003', 'X', 'Tata Boga'),
('KLS-0004', 'XI', 'Tata Busana'),
('KLS-0005', 'X', 'Seni Musik');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_pembayaran`
--

CREATE TABLE `tb_pembayaran` (
  `id_pembayaran` varchar(11) NOT NULL,
  `status` enum('Belum Lunas','Sudah Lunas') NOT NULL,
  `nisn` varchar(10) NOT NULL,
  `tgl_bayar` date NOT NULL,
  `tgl_terakhir_bayar` date NOT NULL,
  `batas_pembayaran` date NOT NULL,
  `jumlah_bulan` varchar(10) NOT NULL,
  `id_spp` varchar(40) NOT NULL,
  `nominal_bayar` varchar(100) NOT NULL,
  `jumlah_bayar` varchar(40) NOT NULL,
  `kembalian` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_pembayaran`
--

INSERT INTO `tb_pembayaran` (`id_pembayaran`, `status`, `nisn`, `tgl_bayar`, `tgl_terakhir_bayar`, `batas_pembayaran`, `jumlah_bulan`, `id_spp`, `nominal_bayar`, `jumlah_bayar`, `kembalian`) VALUES
('0002', 'Sudah Lunas', '660001', '2023-05-16', '2023-06-01', '2023-05-01', '1', 'SPP-0001', '200000', '220000', '20000'),
('0003', 'Sudah Lunas', '660002', '2023-05-16', '2023-06-01', '2023-05-01', '1', 'SPP-0001', '200000', '300000', '100000');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_petugas`
--

CREATE TABLE `tb_petugas` (
  `id_petugas` varchar(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(32) NOT NULL,
  `nama_petugas` varchar(35) NOT NULL,
  `level` enum('admin','petugas','siswa') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_petugas`
--

INSERT INTO `tb_petugas` (`id_petugas`, `username`, `password`, `nama_petugas`, `level`) VALUES
('PTG-001', 'admin', 'admin123', 'Maulana', 'admin'),
('PTG-002', 'petugas', 'petugas123', 'Perdi', 'petugas'),
('PTG-003', 'zaki', 'zaki123', 'Mochammad Zaki Yamani', 'siswa'),
('PTG-004', 'Imamzz', 'imam123', 'imam123', 'admin');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_siswa`
--

CREATE TABLE `tb_siswa` (
  `nisn` varchar(10) NOT NULL,
  `nis` varchar(8) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `id_kelas` varchar(11) NOT NULL,
  `nama_kelas` varchar(10) NOT NULL,
  `alamat` text NOT NULL,
  `no_telp` varchar(13) NOT NULL,
  `id_spp` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_siswa`
--

INSERT INTO `tb_siswa` (`nisn`, `nis`, `nama`, `id_kelas`, `nama_kelas`, `alamat`, `no_telp`, `id_spp`) VALUES
('660001', 'NIS-0001', 'Ahmad Royhan Azmi', 'KLS-0003', 'X', 'Jl. Azmi Rt 5', '082934724354', 'SPP-0001'),
('660002', 'NIS-0002', 'Andrean', 'KLS-0005', 'X', 'Jl. Simpang Mosa', '081237124453', 'SPP-0002'),
('660003', 'NIS-0003', 'M. Perdi Fratama', 'KLS-0003', 'X', 'jl. Alalak Tengah', '083142106988', 'SPP-0003'),
('660004', 'NIS-0004', 'Ahmad Maulana Alaudin', 'KLS-0005', 'X', 'Jl. Berangas', '081968234872', 'SPP-0004'),
('660005', 'NIS-0005', 'Imam Zikri Maulidi', 'KLS-0001', 'X', 'Jl. Simpang Gusti', '088273482534', 'SPP-0005');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_spp`
--

CREATE TABLE `tb_spp` (
  `id_spp` varchar(11) NOT NULL,
  `tahun` int(11) NOT NULL,
  `nominal` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_spp`
--

INSERT INTO `tb_spp` (`id_spp`, `tahun`, `nominal`) VALUES
('SPP-1234', 2023, 'RP. 100.000,00'),
('SPP-2664', 2017, 'Rp. 250.000,00'),
('SPP-7434', 2021, 'Rp. 608.000,00'),
('SPP-9895', 2023, 'Rp. 500.000,00');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `cek_pembayaran`
--
ALTER TABLE `cek_pembayaran`
  ADD PRIMARY KEY (`nisn`),
  ADD KEY `nama` (`nama`),
  ADD KEY `no_telp` (`no_telp`);

--
-- Indeks untuk tabel `tb_kelas`
--
ALTER TABLE `tb_kelas`
  ADD PRIMARY KEY (`id_kelas`),
  ADD KEY `nama_kelas` (`nama_kelas`);

--
-- Indeks untuk tabel `tb_pembayaran`
--
ALTER TABLE `tb_pembayaran`
  ADD PRIMARY KEY (`id_pembayaran`),
  ADD KEY `id_spp` (`id_spp`),
  ADD KEY `nisn` (`nisn`);

--
-- Indeks untuk tabel `tb_petugas`
--
ALTER TABLE `tb_petugas`
  ADD PRIMARY KEY (`id_petugas`);

--
-- Indeks untuk tabel `tb_siswa`
--
ALTER TABLE `tb_siswa`
  ADD PRIMARY KEY (`nisn`),
  ADD KEY `id_kelas` (`id_kelas`),
  ADD KEY `id_spp` (`id_spp`),
  ADD KEY `nama` (`nama`),
  ADD KEY `no_telp` (`no_telp`),
  ADD KEY `nama_kelas` (`nama_kelas`);

--
-- Indeks untuk tabel `tb_spp`
--
ALTER TABLE `tb_spp`
  ADD PRIMARY KEY (`id_spp`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `cek_pembayaran`
--
ALTER TABLE `cek_pembayaran`
  ADD CONSTRAINT `cek_pembayaran_ibfk_1` FOREIGN KEY (`nisn`) REFERENCES `tb_siswa` (`nisn`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cek_pembayaran_ibfk_2` FOREIGN KEY (`nama`) REFERENCES `tb_siswa` (`nama`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cek_pembayaran_ibfk_3` FOREIGN KEY (`no_telp`) REFERENCES `tb_siswa` (`no_telp`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tb_pembayaran`
--
ALTER TABLE `tb_pembayaran`
  ADD CONSTRAINT `tb_pembayaran_ibfk_1` FOREIGN KEY (`nisn`) REFERENCES `tb_siswa` (`nisn`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_pembayaran_ibfk_5` FOREIGN KEY (`id_spp`) REFERENCES `tb_siswa` (`id_spp`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tb_siswa`
--
ALTER TABLE `tb_siswa`
  ADD CONSTRAINT `tb_siswa_ibfk_3` FOREIGN KEY (`id_kelas`) REFERENCES `tb_kelas` (`id_kelas`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_siswa_ibfk_4` FOREIGN KEY (`nama_kelas`) REFERENCES `tb_kelas` (`nama_kelas`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
