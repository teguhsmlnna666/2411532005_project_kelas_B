package praktikum5;

public class Main {

	public static void main(String[] args) {
		// objek mobil
		Mobil mobil = new Mobil("Toyota", "Avanza", 2021, "Automatic");
		mobil.tampilkanInfo();
		mobil.nyalakanMesin();
		System.out.println("Jenis bahan bakar: " + mobil.jenisBahanBakar());
		mobil.infoKonsumsi();
		mobil.fiturMobil();
		System.out.println();
		
		// objek bus
		Bus bus = new Bus("Mercedes-Benz", "Bus Pariwisata", 2018, "Eksekutif");
		bus.tampilkanInfo();
		bus.nyalakanMesin();
		System.out.println("Jenis bahan bakar: " + bus.jenisBahanBakar());
		bus.infoKonsumsi();
		System.out.println("Kapasitas penumpang: " + bus.kapasitasPenumpang() + " penumpang");
		bus.fiturBus();
		Bus.JadwalPerjalanan jadwal = bus.new JadwalPerjalanan("Jakarta - Bandung", "08.00");
		jadwal.tampilkanJadwal();	
		System.out.println();
		
		// objek pesawat
		Pesawat pesawat = new Pesawat("Garuda", "Boeing 737", 100, "Garuda Indonesia", "Ekonomi");
		pesawat.tampilkanInfo();
		pesawat.nyalakanMesin();
		System.out.println("Jenis Bahan Bakar: " + pesawat.jenisBahanBakar());
	}
}
