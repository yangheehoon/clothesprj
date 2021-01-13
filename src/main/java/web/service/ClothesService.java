package web.service;

import java.util.List;

import web.dao.ClothesDao;
import web.model.Clothes;

public class ClothesService {
	
	ClothesDao clothesdao = new ClothesDao();
	
	public List<Clothes> ServiceClothesList() {
		return clothesdao.SelectClothesList();
	}
	public void ServiceInsertClothes(String name,int price,String description,String files) {
		clothesdao.InsertClothes(name, price, description, files);
	}
}
