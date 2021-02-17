package web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.dao.ClothesDao;
import web.model.Clothes;

@Service
public class ClothesService {
	@Autowired
	ClothesDao clothesdao;
	
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
	public void ServiceDelClothes(int delnum) {
		clothesdao.DelClothes(delnum);		
	}
}
