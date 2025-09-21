package DAO;

import java.util.List;
import model.Service;

public interface ServiceDAO {
	void save(Service service);
	List<Service> show();
	public void delete(String id);
	public void update(Service service);
}
