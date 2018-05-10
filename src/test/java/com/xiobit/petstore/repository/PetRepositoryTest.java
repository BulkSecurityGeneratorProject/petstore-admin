package com.xiobit.petstore.repository;

import com.xiobit.petstore.domain.Pet;
import com.xiobit.petstore.domain.enumeration.Gender;
import com.xiobit.petstore.domain.enumeration.PetType;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class PetRepositoryTest {

    @MockBean
    private PetRepository petRepository;

    @Before
    public void setup() {

        Pet pet1 = new Pet();
        pet1.setId(1L);
        pet1.setName("Hercules");
        pet1.setWeight(10.5F);
        pet1.setType(PetType.DOG);
        pet1.setGender(Gender.MALE);

        Pet pet2 = new Pet();
        pet2.setId(2L);
        pet2.setName("Capitan Nemo");
        pet2.setWeight(1.9F);
        pet2.setType(PetType.CANARY);
        pet2.setGender(Gender.FEMALE);

        List<Pet> pets = new ArrayList<>();
        pets.add(pet1);
        pets.add(pet2);

        Mockito.when(petRepository.findAll()).thenReturn(pets);
        Mockito.when(petRepository.findOne(2L)).thenReturn(pet2);
    }

    @Test
    public void findAllPetsTest() {
        List<Pet> mascotas = petRepository.findAll();
        assertEquals(2, mascotas.size());
    }

    @Test
    public void findOnePetsTest() {
        Pet mascota = petRepository.findOne(2L);
        // ...
    }

    @Test
    public void savePetTest() {
        // implementar
    }

}
