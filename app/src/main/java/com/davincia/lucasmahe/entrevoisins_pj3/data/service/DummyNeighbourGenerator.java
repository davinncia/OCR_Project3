package com.davincia.lucasmahe.entrevoisins_pj3.data.service;

import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {

    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "4 rue Delamarche", "06.43.56.04.26", "moi123@mail.fr", "J'adore bricoler et jardiner. \nJe recherche quelqu'un pour m'enseigner le piano en échange.", false),
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e","4 rue Delamarche", "06.43.56.04.26", "moi123@mail.fr", "J'adore bricoler et jardiner. \nJe recherche quelqu'un pour m'enseigner le piano en échange.", false),
            new Neighbour(3, "Chloé", "http://i.pravatar.cc/150?u=a042581f4e29026704f", "4 rue Delamarche", "06.43.56.04.26", "moi123@mail.fr", "J'adore bricoler et jardiner. \nJe recherche quelqu'un pour m'enseigner le piano en échange.", false),
            new Neighbour(4, "Vincent", "http://i.pravatar.cc/150?u=a042581f4e29026704a", "4 rue Delamarche", "06.43.56.04.26", "moi123@mail.fr", "J'adore bricoler et jardiner. \nJe recherche quelqu'un pour m'enseigner le piano en échange.", false),
            new Neighbour(5, "Elodie", "http://i.pravatar.cc/150?u=a042581f4e29026704b", "4 rue Delamarche", "06.43.56.04.26", "moi123@mail.fr", "J'adore bricoler et jardiner. \nJe recherche quelqu'un pour m'enseigner le piano en échange.", false),
            new Neighbour(6, "Sylvain", "http://i.pravatar.cc/150?u=a042581f4e29026704c", "4 rue Delamarche", "06.43.56.04.26", "moi123@mail.fr", "J'adore bricoler et jardiner. \nJe recherche quelqu'un pour m'enseigner le piano en échange.", false),
            new Neighbour(7, "Laetitia", "http://i.pravatar.cc/150?u=a042581f4e29026703d", "4 rue Delamarche", "06.43.56.04.26", "moi123@mail.fr", "J'adore bricoler et jardiner. \nJe recherche quelqu'un pour m'enseigner le piano en échange.", false),
            new Neighbour(8, "Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b", "4 rue Delamarche", "06.43.56.04.26", "moi123@mail.fr", "J'adore bricoler et jardiner. \nJe recherche quelqu'un pour m'enseigner le piano en échange.", false),
            new Neighbour(9, "Joseph", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "4 rue Delamarche", "06.43.56.04.26", "moi123@mail.fr", "J'adore bricoler et jardiner. \nJe recherche quelqu'un pour m'enseigner le piano en échange.", false),
            new Neighbour(10, "Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d", "4 rue Delamarche", "06.43.56.04.26", "moi123@mail.fr", "J'adore bricoler et jardiner. \nJe recherche quelqu'un pour m'enseigner le piano en échange.", false),
            new Neighbour(11, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d", "4 rue Delamarche", "06.43.56.04.26", "moi123@mail.fr", "J'adore bricoler et jardiner. \nJe recherche quelqu'un pour m'enseigner le piano en échange.", false),
            new Neighbour(12, "Ludovic", "http://i.pravatar.cc/150?u=a042581f3e39026702d", "4 rue Delamarche", "06.43.56.04.26", "moi123@mail.fr", "J'adore bricoler et jardiner. \nJe recherche quelqu'un pour m'enseigner le piano en échange.",false)
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }
}
