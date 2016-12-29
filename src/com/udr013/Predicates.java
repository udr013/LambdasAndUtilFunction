package com.udr013;


import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class Predicates {

	public static void main(String[] args) {

		List<Album> albums = new AlbumListStub().getAlbumList();

		for(Album album : albums){
			if(album.getArtist().equals("Beastie Boys")){
				System.out.println(album);
			}
		}

		albums.forEach(new Consumer<Album>() {
			@Override
			public void accept(Album album) {
				if (album.getYear() < 1980) {
					System.out.println(album);
				}
			}
		});

		//notice it's one statement all together
		albums.forEach(album -> {
			if (album.getYear() < 1980) {
				System.out.println(album);
			}
		});

		String sep = "__________";

		//we declare a predicate with a lambda
		Predicate<Album> myPredicate = album -> album.getYear() < 1986;
		Predicate<Album> abovePredicate = album -> album.getYear() > 1970;
//		myPredicate = album -> album.getYear() > 1987;

		printAlbums(albums,  sep, myPredicate);
		printAlbums(albums, sep, album -> album.getArtist().contains("M"));

		//we still can use it like this
		printAlbums(albums, sep, new Predicate<Album>() {
					@Override
					public boolean test(Album album) {
						return album.getTitle().contains("of");
					}
				});

		//chain with and/or
		System.out.println("this is tested with two chained predicates: "+ myPredicate.and(abovePredicate).test
				(albums.get(3)));

		//other  examples of predicates are IntPredicate, LongPredicate, DoublePredicate
		IntPredicate intPredicate = i -> i > 10;
		System.out.println(intPredicate.test(9));

		//BiPredicate
		BiPredicate<Album, Album>  biPredicate = (a, b) -> a.getYear() > b.getYear();
		System.out.println(biPredicate.test(albums.get(0), albums.get(1)));


	}






	private static void printAlbums(List<Album> albums, String string, Predicate<Album> predicate){
		System.out.println(string);
		albums.forEach(album -> {
			if(predicate.test(album)){
				System.out.println(album.getYear());
			}
		});

//		for(Album album : albums){
//			if(predicate.test(album)){
//				System.out.println(album.getTitle());
//			}
//		}
	}
}