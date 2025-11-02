package praktikum5;

public class Bus extends Kendaraan implements TransportasiUmum {
	private String kelasBus;

	public Bus(String merk, String model, int tahunProduksi, String kelasBus) {
		super(merk, model, tahunProduksi);
		this.kelasBus = kelasBus;
	}

	@Override
	public void nyalakanMesin() {
		System.out.println("Nyalakn Mesin: Putar kunci untuk menyalakan mesin");
	}

	@Override
	public String jenisBahanBakar() {
		return "Solar";
	}

	@Override
	public int kapasitasPenumpang() {
		return 45;
	}
	
	public void fiturBus() {
		System.out.println("Fitur Bus: Dilengkapi kursi nyaman dan fasilitas hiburan");
	}
	
	public class JadwalPerjalanan {
		String rute;
		String waktuBerangkat;
		
		public JadwalPerjalanan(String rute, String waktuBerangkat) {
			this.rute = rute;
			this.waktuBerangkat = waktuBerangkat;
		}
		
		public void tampilkanJadwal() {
			System.out.println("Jadwal Perjalanan: Rute " + rute + ", Waktu Berangkat " + waktuBerangkat);
		}
		
	}
}
