package com.github.zaza.kuchennerewolucje;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Optional;

import org.geojson.Feature;
import org.junit.Test;

import com.github.zaza.kuchennerewolucje.model.Episode;

public class EpisodeToFeatureMapperTest {
	@Test
	public void applyFeatureNotCreatedForEmptyEpisodeMap() throws Exception {
		EpisodeToFeatureMapper mapper = new EpisodeToFeatureMapper(null);

		Optional<Feature> feature = mapper.apply(new Episode(new HashMap<>()));

		assertFalse(feature.isPresent());
	}

	@Test
	public void hasZipCode() throws Exception {
		assertTrue(EpisodeToFeatureMapper.hasZipCode("Flisacka 35B, 34-460 Gmina Szczawnica"));
		assertTrue(EpisodeToFeatureMapper.hasZipCode("Michała Ogińskiego 5, 03-318 Warszawa"));
		assertTrue(EpisodeToFeatureMapper.hasZipCode("Maratońska 57, 94-102 Łódź"));
		assertTrue(EpisodeToFeatureMapper.hasZipCode("03-318 Warszawa"));
		assertFalse(EpisodeToFeatureMapper.hasZipCode("Warszawa"));
		assertFalse(EpisodeToFeatureMapper.hasZipCode("Gmina Szczawnica"));
	}

	@Test
	public void getCity() throws Exception {
		assertEquals("Gmina Szczawnica", EpisodeToFeatureMapper.getCity("Flisacka 35B, 34-460 Gmina Szczawnica"));
		assertEquals("Warszawa", EpisodeToFeatureMapper.getCity("Michała Ogińskiego 5, 03-318 Warszawa"));
		assertEquals("Łódź", EpisodeToFeatureMapper.getCity("Maratońska 57, 94-102 Łódź"));
		assertEquals("Warszawa", EpisodeToFeatureMapper.getCity("03-318 Warszawa"));
		assertEquals("Warszawa", EpisodeToFeatureMapper.getCity("Warszawa"));
		assertEquals("Gmina Szczawnica", EpisodeToFeatureMapper.getCity("Gmina Szczawnica"));
	}
}
