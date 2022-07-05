import 'package:flutter/material.dart';

const users = [
  userVraj,
  userVikram,
  userShivam,
  userLalit,
  userAyush,
  userHemendra,
  userKrithik,
  userAtharva,
  userPrajwal,
];

const userVraj = DemoUser(
  id: 'vraj11',
  name: 'Vraj Patel',
  image: 'https://avatars.githubusercontent.com/u/49311691?v=4',
);

const userVikram = DemoUser(
  id: 'vikram57',
  name: 'Vikram Singh Chouhan',
  image:
      'https://media-exp1.licdn.com/dms/image/C4E03AQEIfSrfmgJkfQ/profile-displayphoto-shrink_100_100/0/1645939575955?e=1657152000&v=beta&t=g_miziphALAEMvpDbyuGibULxikIH6uzaA21FkN690M',
);

const userShivam = DemoUser(
  id: 'shivam',
  name: 'Shivam Chaudhary',
  image:
      'https://media-exp1.licdn.com/dms/image/C4D03AQFYzcwMCe_iuw/profile-displayphoto-shrink_200_200/0/1602897122809?e=1657152000&v=beta&t=11krhSln-Sa5W-9CTLoCWfSGhFfg_QJm5OPOkpS9OFM',
);

const userLalit = DemoUser(
  id: 'lalit',
  name: 'Lalit Meghwal',
  image:
      'https://media-exp1.licdn.com/dms/image/C5603AQGJiXE_TtdMzg/profile-displayphoto-shrink_200_200/0/1642570934477?e=1657152000&v=beta&t=aTymubbj8AjFCRW5yWIHsBp7dopT_ZIl_yfaYqa4mXs',
);

const userAyush = DemoUser(
  id: 'ayush',
  name: 'Ayush Kumar',
  image:
      'https://media-exp1.licdn.com/dms/image/C4E03AQHM1JmomnqMMA/profile-displayphoto-shrink_200_200/0/1631517726093?e=1657152000&v=beta&t=bbnyN5pJjZPXXHFVz2HW5QJBz9gokKptBszwpqAX7XA',
);

const userHemendra = DemoUser(
  id: 'hemendra',
  name: 'Hemendra Singh',
  image:
      'https://randompicturegenerator.com/img/dragon-generator/ga12007721fda26c65d8dc2a496927dbbe01563780be53e9c887137beb8f635b6c07093a9e07c12aa09676ac93ae5ef50_640.jpg',
);

const userKrithik = DemoUser(
  id: 'krithik',
  name: 'Krithik Jain',
  image:
      'https://media-exp1.licdn.com/dms/image/C5603AQHJtiqCVgImWQ/profile-displayphoto-shrink_200_200/0/1585552390283?e=1657152000&v=beta&t=55z9m75NEE3oiLDtlBTsSPgcZav2t2WZBDN7hXIa3pY',
);
const userAtharva = DemoUser(
  id: 'atharva',
  name: 'Atharva Naik',
  image:
      'https://randompicturegenerator.com/img/lion-generator/g4d6063761c049a3bf28e6826d7f81bda1bc0a1e0574355f3e1f9684345c1750a320205fe5fca841ad59550d01475bbb6_640.jpg',
);
const userPrajwal = DemoUser(
  id: 'prajwal',
  name: 'Prajwal Premdas',
  image:
      'https://media-exp1.licdn.com/dms/image/C5603AQEmEzk0FX44KA/profile-displayphoto-shrink_100_100/0/1613505917664?e=1657152000&v=beta&t=_IP4d_8TPWxVOMqfAPGlapcHQg3KaU-E_1nY5w6iTBE',
);

@immutable
class DemoUser {
  final String id;
  final String name;
  final String image;

  const DemoUser({
    required this.id,
    required this.name,
    required this.image,
  });
}
