package web.service;

import java.util.List;

import web.dao.ClothesDao;
import web.model.Clothes;

public class ClothesService {
	
	ClothesDao clothesdao = new ClothesDao();
	
	public List<Clothes> ServiceClothesList(String query,int page) {
		return clothesdao.SelectClothesList(query,page);
	}
	public int ServiceClothesCount(String query) {
		return clothesdao.SelectClothesCount(query);
	}
	public void ServiceInsertClothes(String name,int price,String description,String files) {
		clothesdao.InsertClothes(name, price, description, files);
	}
	public Clothes ServiceClothesDetail(int num) {
		return clothesdao.SelectClothesDetail(num);
	}
}
