package com.rumahgugun.pilem.utils

import com.rumahgugun.pilem.data.source.local.entity.MovieEntity
import com.rumahgugun.pilem.data.source.local.entity.TvEntity
import com.rumahgugun.pilem.data.source.remote.response.MovieResponse
import com.rumahgugun.pilem.data.source.remote.response.TvResponse
import java.util.ArrayList

object DataDummy {
    fun generateDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()
        movies.add(
            MovieEntity(
                "1",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                "A Star Is Born",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "2h 16m",
                "75%"
            )
        )
        movies.add(
            MovieEntity(
                "2",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                "Alita: Battle Angel",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "2h 2m",
                "72%"
            )
        )
        movies.add(
            MovieEntity(
                "3",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xLPffWMhMj1l50ND3KchMjYoKmE.jpg",
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "2h 33m",
                "69%"
            )
        )
        movies.add(
            MovieEntity(
                "4",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
                "Bohemian Rhapsody",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "2h 15m",
                "80%"
            )
        )
        movies.add(
            MovieEntity(
                "5",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg",
                "Cold Pursuit",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "1h 59m",
                "57%"
            )
        )
        movies.add(
            MovieEntity(
                "6",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg",
                "Creed II",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "2h 10m",
                "69%"
            )
        )
        movies.add(
            MovieEntity(
                "7",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg",
                "Glass",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "2h 9m",
                "67%"
            )
        )
        movies.add(
            MovieEntity(
                "8",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xvx4Yhf0DVH8G4LzNISpMfFBDy2.jpg",
                "How to Train Your Dragon: The Hidden World",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "1h 44m",
                "78%"
            )
        )
        movies.add(
            MovieEntity(
                "9",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
                "Avengers: Infinity War",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "2h 29m",
                "83%"
            )
        )
        movies.add(
            MovieEntity(
                "10",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gLhYg9NIvIPKVRTtvzCWnp1qJWG.jpg",
                "Mortal Engines",
                "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                "2h 9m",
                "61%"
            )
        )
        return movies
    }

    fun generateDummyTvs(): List<TvEntity> {
        val item = ArrayList<TvEntity>()
        item.add(
            TvEntity(
                "1",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                "The Arrow",
                "Panah adalah menceritakan kembali petualangan dari legendaris DC pahlawan Green Arrow",
                "42m",
                "66%"
            )
        )
        item.add(
            TvEntity(
                "2",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nclcURTdlqVbDr6HPmrHC5X4qUu.jpg",
                "Doom Patrol",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find. ",
                "49m",
                "76%"
            )
        )
        item.add(
            TvEntity(
                "3",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1YIJYarVZKH3nq89n1lVAxeVktg.jpg",
                "ドラゴンボール",
                "Dahulu kala di pegunungan, seorang master pertempuran yang dikenal sebagai Gohan menemukan seorang bocah aneh yang ia beri nama Goku. Gohan membesarkannya dan melatih Goku dalam seni bela diri sampai dia mati. Bocah muda dan sangat kuat itu sendirian, tetapi mudah dikelola. Kemudian suatu hari, Goku bertemu dengan seorang gadis remaja bernama Bulma, yang pencariannya untuk bola naga membawanya ke rumah Goku. Bersama-sama, mereka berangkat untuk menemukan ketujuh bola naga dalam sebuah petualangan yang akan mengubah hidup Goku selamanya. Lihat bagaimana Goku bertemu teman-teman seumur hidupnya Bulma, Yamcha, Krillin, Master Roshi dan banyak lagi.",
                "25m",
                "81%"
            )
        )
        item.add(
            TvEntity(
                "4",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/r6SjKZSsVbdwVZhdutu4qvRe63c.jpg",
                "フェアリーテイル",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                "25m",
                "78%"
            )
        )
        item.add(
            TvEntity(
                "5",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/q3E71oY6qgAEiw6YZIHDlHSLwer.jpg",
                "Family Guy",
                "Seri animasi animasi Freakin 'Sweet yang sakit, terpelintir, dan salah, menampilkan petualangan keluarga Griffin yang disfungsional. Peter yang kikuk dan Lois yang sudah lama menderita memiliki tiga anak. Stewie (bayi yang brilian tetapi sadis yang bertekad membunuh ibunya dan mengambil alih dunia), Meg (yang tertua, dan merupakan gadis yang paling tidak populer di kota) dan Chris (anak tengah, dia tidak terlalu cerdas tetapi memiliki hasrat untuk film ). Anggota terakhir keluarga itu adalah Brian - anjing yang bisa bicara dan lebih dari sekadar hewan peliharaan, ia menjaga Stewie, sementara menghirup Martinis dan memilah-milah masalah hidupnya sendiri.",
                "22m",
                "70%"
            )
        )
        item.add(
            TvEntity(
                "6",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
                "The Flash",
                "Setelah akselerator partikel menyebabkan badai aneh, Penyelidik CSI Barry Allen disambar petir dan jatuh koma. Beberapa bulan kemudian dia terbangun dengan kekuatan kecepatan super, memberinya kemampuan untuk bergerak melalui Central City seperti malaikat penjaga yang tak terlihat. Meskipun awalnya senang dengan kekuatan barunya, Barry terkejut menemukan bahwa dia bukan satu-satunya 'manusia meta' yang diciptakan setelah ledakan akselerator - dan tidak semua orang menggunakan kekuatan baru mereka untuk kebaikan. Barry bermitra dengan S.T.A.R. Lab dan mendedikasikan hidupnya untuk melindungi yang tidak bersalah. Untuk saat ini, hanya beberapa teman dekat dan rekan yang tahu bahwa Barry secara harfiah adalah manusia tercepat, tetapi tidak lama sebelum dunia mengetahui apa yang menjadi Barry Allen ... The Flash.",
                "44m",
                "77%"
            )
        )
        item.add(
            TvEntity(
                "7",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5tSHzkJ1HBnyGdcpr6wSyw7jYnJ.jpg",
                "Gotham",
                "Semua orang tahu nama Komisaris Gordon. Dia adalah salah satu musuh terbesar dunia kejahatan, seorang pria yang reputasinya identik dengan hukum dan ketertiban. Tapi apa yang diketahui tentang kisah Gordon dan kenaikannya dari detektif pemula ke Komisaris Polisi? Apa yang diperlukan untuk menavigasi berbagai lapisan korupsi yang diam-diam memerintah Kota Gotham, tempat bertelurnya penjahat paling ikonik di dunia? Dan keadaan apa yang menciptakan mereka - persona yang lebih besar dari kehidupan yang akan menjadi Catwoman, The Penguin, The Riddler, Two-Face dan The Joker?",
                "43m",
                "75%"
            )
        )
        item.add(
            TvEntity(
                "8",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "Grey's Anatomy",
                "Ikuti kehidupan pribadi dan profesional sekelompok dokter di Rumah Sakit Gray Sloan Memorial di Seattle.",
                "43m",
                "82%"
            )
        )
        item.add(
            TvEntity(
                "9",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5nSSkcM3TgpllZ7yTyBOQEgAX36.jpg",
                "Hanna",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "50m",
                "75%"
            )
        )
        item.add(
            TvEntity(
                "10",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xGpd4wMIWAQ9MukvCx5k5xK7jft.jpg",
                "Iron Fist",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "55m",
                "66%"
            )
        )
        return item
    }
    fun generateRemoteDummyMovies(): List<MovieResponse> {
        val movies = ArrayList<MovieResponse>()
        movies.add(
            MovieResponse(
                "1",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                "A Star Is Born",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "2h 16m",
                "75%"
            )
        )
        movies.add(
            MovieResponse(
                "2",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                "Alita: Battle Angel",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "2h 2m",
                "72%"
            )
        )
        movies.add(
            MovieResponse(
                "3",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xLPffWMhMj1l50ND3KchMjYoKmE.jpg",
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "2h 33m",
                "69%"
            )
        )
        movies.add(
            MovieResponse(
                "4",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
                "Bohemian Rhapsody",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "2h 15m",
                "80%"
            )
        )
        movies.add(
            MovieResponse(
                "5",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg",
                "Cold Pursuit",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "1h 59m",
                "57%"
            )
        )
        movies.add(
            MovieResponse(
                "6",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg",
                "Creed II",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "2h 10m",
                "69%"
            )
        )
        movies.add(
            MovieResponse(
                "7",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg",
                "Glass",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "2h 9m",
                "67%"
            )
        )
        movies.add(
            MovieResponse(
                "8",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xvx4Yhf0DVH8G4LzNISpMfFBDy2.jpg",
                "How to Train Your Dragon: The Hidden World",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "1h 44m",
                "78%"
            )
        )
        movies.add(
            MovieResponse(
                "9",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
                "Avengers: Infinity War",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "2h 29m",
                "83%"
            )
        )
        movies.add(
            MovieResponse(
                "10",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gLhYg9NIvIPKVRTtvzCWnp1qJWG.jpg",
                "Mortal Engines",
                "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                "2h 9m",
                "61%"
            )
        )
        return movies
    }

    fun generateRemoteDummyTvs(): List<TvResponse> {
        val item = ArrayList<TvResponse>()
        item.add(
            TvResponse(
                "1",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                "The Arrow",
                "Panah adalah menceritakan kembali petualangan dari legendaris DC pahlawan Green Arrow",
                "42m",
                "66%"
            )
        )
        item.add(
            TvResponse(
                "2",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nclcURTdlqVbDr6HPmrHC5X4qUu.jpg",
                "Doom Patrol",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find. ",
                "49m",
                "76%"
            )
        )
        item.add(
            TvResponse(
                "3",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1YIJYarVZKH3nq89n1lVAxeVktg.jpg",
                "ドラゴンボール",
                "Dahulu kala di pegunungan, seorang master pertempuran yang dikenal sebagai Gohan menemukan seorang bocah aneh yang ia beri nama Goku. Gohan membesarkannya dan melatih Goku dalam seni bela diri sampai dia mati. Bocah muda dan sangat kuat itu sendirian, tetapi mudah dikelola. Kemudian suatu hari, Goku bertemu dengan seorang gadis remaja bernama Bulma, yang pencariannya untuk bola naga membawanya ke rumah Goku. Bersama-sama, mereka berangkat untuk menemukan ketujuh bola naga dalam sebuah petualangan yang akan mengubah hidup Goku selamanya. Lihat bagaimana Goku bertemu teman-teman seumur hidupnya Bulma, Yamcha, Krillin, Master Roshi dan banyak lagi.",
                "25m",
                "81%"
            )
        )
        item.add(
            TvResponse(
                "4",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/r6SjKZSsVbdwVZhdutu4qvRe63c.jpg",
                "フェアリーテイル",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                "25m",
                "78%"
            )
        )
        item.add(
            TvResponse(
                "5",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/q3E71oY6qgAEiw6YZIHDlHSLwer.jpg",
                "Family Guy",
                "Seri animasi animasi Freakin 'Sweet yang sakit, terpelintir, dan salah, menampilkan petualangan keluarga Griffin yang disfungsional. Peter yang kikuk dan Lois yang sudah lama menderita memiliki tiga anak. Stewie (bayi yang brilian tetapi sadis yang bertekad membunuh ibunya dan mengambil alih dunia), Meg (yang tertua, dan merupakan gadis yang paling tidak populer di kota) dan Chris (anak tengah, dia tidak terlalu cerdas tetapi memiliki hasrat untuk film ). Anggota terakhir keluarga itu adalah Brian - anjing yang bisa bicara dan lebih dari sekadar hewan peliharaan, ia menjaga Stewie, sementara menghirup Martinis dan memilah-milah masalah hidupnya sendiri.",
                "22m",
                "70%"
            )
        )
        item.add(
            TvResponse(
                "6",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
                "The Flash",
                "Setelah akselerator partikel menyebabkan badai aneh, Penyelidik CSI Barry Allen disambar petir dan jatuh koma. Beberapa bulan kemudian dia terbangun dengan kekuatan kecepatan super, memberinya kemampuan untuk bergerak melalui Central City seperti malaikat penjaga yang tak terlihat. Meskipun awalnya senang dengan kekuatan barunya, Barry terkejut menemukan bahwa dia bukan satu-satunya 'manusia meta' yang diciptakan setelah ledakan akselerator - dan tidak semua orang menggunakan kekuatan baru mereka untuk kebaikan. Barry bermitra dengan S.T.A.R. Lab dan mendedikasikan hidupnya untuk melindungi yang tidak bersalah. Untuk saat ini, hanya beberapa teman dekat dan rekan yang tahu bahwa Barry secara harfiah adalah manusia tercepat, tetapi tidak lama sebelum dunia mengetahui apa yang menjadi Barry Allen ... The Flash.",
                "44m",
                "77%"
            )
        )
        item.add(
            TvResponse(
                "7",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5tSHzkJ1HBnyGdcpr6wSyw7jYnJ.jpg",
                "Gotham",
                "Semua orang tahu nama Komisaris Gordon. Dia adalah salah satu musuh terbesar dunia kejahatan, seorang pria yang reputasinya identik dengan hukum dan ketertiban. Tapi apa yang diketahui tentang kisah Gordon dan kenaikannya dari detektif pemula ke Komisaris Polisi? Apa yang diperlukan untuk menavigasi berbagai lapisan korupsi yang diam-diam memerintah Kota Gotham, tempat bertelurnya penjahat paling ikonik di dunia? Dan keadaan apa yang menciptakan mereka - persona yang lebih besar dari kehidupan yang akan menjadi Catwoman, The Penguin, The Riddler, Two-Face dan The Joker?",
                "43m",
                "75%"
            )
        )
        item.add(
            TvResponse(
                "8",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "Grey's Anatomy",
                "Ikuti kehidupan pribadi dan profesional sekelompok dokter di Rumah Sakit Gray Sloan Memorial di Seattle.",
                "43m",
                "82%"
            )
        )
        item.add(
            TvResponse(
                "9",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5nSSkcM3TgpllZ7yTyBOQEgAX36.jpg",
                "Hanna",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "50m",
                "75%"
            )
        )
        item.add(
            TvResponse(
                "10",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xGpd4wMIWAQ9MukvCx5k5xK7jft.jpg",
                "Iron Fist",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "55m",
                "66%"
            )
        )
        return item
    }
}