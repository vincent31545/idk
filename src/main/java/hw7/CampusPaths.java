package hw7;

import java.io.IOException;

public class CampusPaths {
	
	// @requires nothing
	// @param nothing (args does not do anything)
	// @effects runs everything
	// @modifies nothing
	// @throws nothing
	// @returns nothing
	public static void main(String[] args) {
		try {
			CampusPathsModel.parseData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CampusPathsUI.acceptInput();
	}
}