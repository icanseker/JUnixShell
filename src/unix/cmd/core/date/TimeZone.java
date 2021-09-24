package unix.cmd.core.date;

/**
 * based on IANA 2021a (Released 2021-01-24)
 *
 */
public interface TimeZone {

	public String name();

	public String id();

	static enum AFRICA implements TimeZone {
		ABIDJAN("Abidjan"), //
		ACCRA("Accra"), //
		ADDIS_ABABA("Addis_Ababa"), //
		ALGIERS("Algiers"), //
		ASMARA("Asmara"), //
		BAMAKO("Bamako"), //
		BANGUI("Bangui"), //
		BANJUL("Banjul"), //
		BISSAU("Bissau"), //
		BLANTYRE("Blantyre"), //
		BRAZZAVILLE("Brazzaville"), //
		BUJUMBURA("Bujumbura"), //
		CAIRO("Cairo"), //
		CASABLANCA("Casablanca"), //
		CEUTA("Ceuta"), //
		CONAKRY("Conakry"), //
		DAKAR("Dakar"), //
		DAR_ES_SALAAM("Dar_es_Salaam"), //
		DJIBOUTI("Djibouti"), //
		DOUALA("Douala"), //
		EL_AAIUN("El_Aaiun"), //
		FREETOWN("Freetown"), //
		GABORONE("Gaborone"), //
		HARARE("Harare"), //
		JOHANNESBURG("Johannesburg"), //
		JUBA("Juba"), //
		KAMPALA("Kampala"), //
		KHARTOUM("Khartoum"), //
		KIGALI("Kigali"), //
		KINSHASA("Kinshasa"), //
		LAGOS("Lagos"), //
		LIBREVILLE("Libreville"), //
		LOME("Lome"), //
		LUANDA("Luanda"), //
		LUBUMBASHI("Lubumbashi"), //
		LUSAKA("Lusaka"), //
		MALABO("Malabo"), //
		MAPUTO("Maputo"), //
		MASERU("Maseru"), //
		MBABANE("Mbabane"), //
		MOGADISHU("Mogadishu"), //
		MONROVIA("Monrovia"), //
		NAIROBI("Nairobi"), //
		NDJAMENA("Ndjamena"), //
		NIAMEY("Niamey"), //
		NOUAKCHOTT("Nouakchott"), //
		OUAGADOUGOU("Ouagadougou"), //
		PORTO_NOVO("Porto-Novo"), //
		SAO_TOME("Sao_Tome"), //
		TRIPOLI("Tripoli"), //
		TUNIS("Tunis"), //
		WINDHOEK("Windhoek"), //
		;

		private final String id;

		private AFRICA(String id) {
			this.id = id;
		}

		@Override
		public String id() {
			return "Africa/" + this.id;
		}
	}

	static enum AMERICA implements TimeZone {
		ADAK("Adak"), //
		ANCHORAGE("Anchorage"), //
		ANGUILLA("Anguilla"), //
		ANTIGUA("Antigua"), //
		ARAGUAINA("Araguaina"), //
		ARGENTINA__BUENOS_AIRES("Argentina/Buenos_Aires"), //
		ARGENTINA__CATAMARCA("Argentina/Catamarca"), //
		ARGENTINA__CORDOBA("Argentina/Cordoba"), //
		ARGENTINA__JUJUY("Argentina/Jujuy"), //
		ARGENTINA__LA_RIOJA("Argentina/La_Rioja"), //
		ARGENTINA__MENDOZA("Argentina/Mendoza"), //
		ARGENTINA__RIO_GALLEGOS("Argentina/Rio_Gallegos"), //
		ARGENTINA__SALTA("Argentina/Salta"), //
		ARGENTINA__SAN_JUAN("Argentina/San_Juan"), //
		ARGENTINA__SAN_LUIS("Argentina/San_Luis"), //
		ARGENTINA__TUCUMAN("Argentina/Tucuman"), //
		ARGENTINA__USHUAIA("Argentina/Ushuaia"), //
		ARUBA("Aruba"), //
		ASUNCION("Asuncion"), //
		ATIKOKAN("Atikokan"), //
		BAHIA("Bahia"), //
		BAHIA_BANDERAS("Bahia_Banderas"), //
		BARBADOS("Barbados"), //
		BELEM("Belem"), //
		BELIZE("Belize"), //
		BLANC_SABLON("Blanc-Sablon"), //
		BOA_VISTA("Boa_Vista"), //
		BOGOTA("Bogota"), //
		BOISE("Boise"), //
		CAMBRIDGE_BAY("Cambridge_Bay"), //
		CAMPO_GRANDE("Campo_Grande"), //
		CANCUN("Cancun"), //
		CARACAS("Caracas"), //
		CAYENNE("Cayenne"), //
		CAYMAN("Cayman"), //
		CHICAGO("Chicago"), //
		CHIHUAHUA("Chihuahua"), //
		COSTA_RICA("Costa_Rica"), //
		CRESTON("Creston"), //
		CUIABA("Cuiaba"), //
		CURACAO("Curacao"), //
		DANMARKSHAVN("Danmarkshavn"), //
		DAWSON("Dawson"), //
		DAWSON_CREEK("Dawson_Creek"), //
		DENVER("Denver"), //
		DETROIT("Detroit"), //
		DOMINICA("Dominica"), //
		EDMONTON("Edmonton"), //
		EIRUNEPE("Eirunepe"), //
		EL_SALVADOR("El_Salvador"), //
		FORT_NELSON("Fort_Nelson"), //
		FORTALEZA("Fortaleza"), //
		GLACE_BAY("Glace_Bay"), //
		GOOSE_BAY("Goose_Bay"), //
		GRAND_TURK("Grand_Turk"), //
		GRENADA("Grenada"), //
		GUADELOUPE("Guadeloupe"), //
		GUATEMALA("Guatemala"), //
		GUAYAQUIL("Guayaquil"), //
		GUYANA("Guyana"), //
		HALIFAX("Halifax"), //
		HAVANA("Havana"), //
		HERMOSILLO("Hermosillo"), //
		INDIANA__INDIANAPOLIS("Indiana/Indianapolis"), //
		INDIANA__KNOX("Indiana/Knox"), //
		INDIANA__MARENGO("Indiana/Marengo"), //
		INDIANA__PETERSBURG("Indiana/Petersburg"), //
		INDIANA__TELL_CITY("Indiana/Tell_City"), //
		INDIANA__VEVAY("Indiana/Vevay"), //
		INDIANA__VINCENNES("Indiana/Vincennes"), //
		INDIANA__WINAMAC("Indiana/Winamac"), //
		INUVIK("Inuvik"), //
		IQALUIT("Iqaluit"), //
		JAMAICA("Jamaica"), //
		JUNEAU("Juneau"), //
		KENTUCKY__LOUISVILLE("Kentucky/Louisville"), //
		KENTUCKY__MONTICELLO("Kentucky/Monticello"), //
		KRALENDIJK("Kralendijk"), //
		LA_PAZ("La_Paz"), //
		LIMA("Lima"), //
		LOS_ANGELES("Los_Angeles"), //
		LOWER_PRINCES("Lower_Princes"), //
		MACEIO("Maceio"), //
		MANAGUA("Managua"), //
		MANAUS("Manaus"), //
		MARIGOT("Marigot"), //
		MARTINIQUE("Martinique"), //
		MATAMOROS("Matamoros"), //
		MAZATLAN("Mazatlan"), //
		MENOMINEE("Menominee"), //
		MERIDA("Merida"), //
		METLAKATLA("Metlakatla"), //
		MEXICO_CITY("Mexico_City"), //
		MIQUELON("Miquelon"), //
		MONCTON("Moncton"), //
		MONTERREY("Monterrey"), //
		MONTEVIDEO("Montevideo"), //
		MONTSERRAT("Montserrat"), //
		NASSAU("Nassau"), //
		NEW_YORK("New_York"), //
		NIPIGON("Nipigon"), //
		NOME("Nome"), //
		NORONHA("Noronha"), //
		NORTH_DAKOTA__BEULAH("North_Dakota/Beulah"), //
		NORTH_DAKOTA__CENTER("North_Dakota/Center"), //
		NORTH_DAKOTA__NEW_SALEM("North_Dakota/New_Salem"), //
		NUUK("Nuuk"), //
		OJINAGA("Ojinaga"), //
		PANAMA("Panama"), //
		PANGNIRTUNG("Pangnirtung"), //
		PARAMARIBO("Paramaribo"), //
		PHOENIX("Phoenix"), //
		PORT_OF_SPAIN("Port_of_Spain"), //
		PORT_AU_PRINCE("Port-au-Prince"), //
		PORTO_VELHO("Porto_Velho"), //
		PUERTO_RICO("Puerto_Rico"), //
		PUNTA_ARENAS("Punta_Arenas"), //
		RAINY_RIVER("Rainy_River"), //
		RANKIN_INLET("Rankin_Inlet"), //
		RECIFE("Recife"), //
		REGINA("Regina"), //
		RESOLUTE("Resolute"), //
		RIO_BRANCO("Rio_Branco"), //
		SANTAREM("Santarem"), //
		SANTIAGO("Santiago"), //
		SANTO_DOMINGO("Santo_Domingo"), //
		SAO_PAULO("Sao_Paulo"), //
		SCORESBYSUND("Scoresbysund"), //
		SITKA("Sitka"), //
		ST_BARTHELEMY("St_Barthelemy"), //
		ST_JOHNS("St_Johns"), //
		ST_KITTS("St_Kitts"), //
		ST_LUCIA("St_Lucia"), //
		ST_THOMAS("St_Thomas"), //
		ST_VINCENT("St_Vincent"), //
		SWIFT_CURRENT("Swift_Current"), //
		TEGUCIGALPA("Tegucigalpa"), //
		THULE("Thule"), //
		THUNDER_BAY("Thunder_Bay"), //
		TIJUANA("Tijuana"), //
		TORONTO("Toronto"), //
		TORTOLA("Tortola"), //
		VANCOUVER("Vancouver"), //
		WHITEHORSE("Whitehorse"), //
		WINNIPEG("Winnipeg"), //
		YAKUTAT("Yakutat"), //
		YELLOWKNIFE("Yellowknife"), //
		;

		private final String id;

		private AMERICA(String id) {
			this.id = id;
		}

		@Override
		public String id() {
			return "America/" + this.id;
		}
	}

	static enum ANTARCTICA implements TimeZone {
		CASEY("Casey"), //
		DAVIS("Davis"), //
		DUMONTDURVILLE("DumontDUrville"), //
		MACQUARIE("Macquarie"), //
		MAWSON("Mawson"), //
		MCMURDO("McMurdo"), //
		PALMER("Palmer"), //
		ROTHERA("Rothera"), //
		SYOWA("Syowa"), //
		TROLL("Troll"), //
		VOSTOK("Vostok"), //
		;

		private final String id;

		private ANTARCTICA(String id) {
			this.id = id;
		}

		@Override
		public String id() {
			return "Antarctica/" + this.id;
		}
	}

	static enum ARCTIC implements TimeZone {
		LONGYEARBYEN("Longyearbyen");

		private final String id;

		private ARCTIC(String id) {
			this.id = id;
		}

		@Override
		public String id() {
			return "Arctic/" + this.id;
		}
	}

	static enum ASIA implements TimeZone {
		ADEN("Aden"), //
		ALMATY("Almaty"), //
		AMMAN("Amman"), //
		ANADYR("Anadyr"), //
		AQTAU("Aqtau"), //
		AQTOBE("Aqtobe"), //
		ASHGABAT("Ashgabat"), //
		ATYRAU("Atyrau"), //
		BAGHDAD("Baghdad"), //
		BAHRAIN("Bahrain"), //
		BAKU("Baku"), //
		BANGKOK("Bangkok"), //
		BARNAUL("Barnaul"), //
		BEIRUT("Beirut"), //
		BISHKEK("Bishkek"), //
		BRUNEI("Brunei"), //
		CHITA("Chita"), //
		CHOIBALSAN("Choibalsan"), //
		COLOMBO("Colombo"), //
		DAMASCUS("Damascus"), //
		DHAKA("Dhaka"), //
		DILI("Dili"), //
		DUBAI("Dubai"), //
		DUSHANBE("Dushanbe"), //
		FAMAGUSTA("Famagusta"), //
		GAZA("Gaza"), //
		HEBRON("Hebron"), //
		HO_CHI_MINH("Ho_Chi_Minh"), //
		HONG_KONG("Hong_Kong"), //
		HOVD("Hovd"), //
		IRKUTSK("Irkutsk"), //
		JAKARTA("Jakarta"), //
		JAYAPURA("Jayapura"), //
		JERUSALEM("Jerusalem"), //
		KABUL("Kabul"), //
		KAMCHATKA("Kamchatka"), //
		KARACHI("Karachi"), //
		KATHMANDU("Kathmandu"), //
		KHANDYGA("Khandyga"), //
		KOLKATA("Kolkata"), //
		KRASNOYARSK("Krasnoyarsk"), //
		KUALA_LUMPUR("Kuala_Lumpur"), //
		KUCHING("Kuching"), //
		KUWAIT("Kuwait"), //
		MACAU("Macau"), //
		MAGADAN("Magadan"), //
		MAKASSAR("Makassar"), //
		MANILA("Manila"), //
		MUSCAT("Muscat"), //
		NICOSIA("Nicosia"), //
		NOVOKUZNETSK("Novokuznetsk"), //
		NOVOSIBIRSK("Novosibirsk"), //
		OMSK("Omsk"), //
		ORAL("Oral"), //
		PHNOM_PENH("Phnom_Penh"), //
		PONTIANAK("Pontianak"), //
		PYONGYANG("Pyongyang"), //
		QATAR("Qatar"), //
		QOSTANAY("Qostanay"), //
		QYZYLORDA("Qyzylorda"), //
		RIYADH("Riyadh"), //
		SAKHALIN("Sakhalin"), //
		SAMARKAND("Samarkand"), //
		SEOUL("Seoul"), //
		SHANGHAI("Shanghai"), //
		SINGAPORE("Singapore"), //
		SREDNEKOLYMSK("Srednekolymsk"), //
		TAIPEI("Taipei"), //
		TASHKENT("Tashkent"), //
		TBILISI("Tbilisi"), //
		TEHRAN("Tehran"), //
		THIMPHU("Thimphu"), //
		TOKYO("Tokyo"), //
		TOMSK("Tomsk"), //
		ULAANBAATAR("Ulaanbaatar"), //
		URUMQI("Urumqi"), //
		UST_NERA("Ust-Nera"), //
		VIENTIANE("Vientiane"), //
		VLADIVOSTOK("Vladivostok"), //
		YAKUTSK("Yakutsk"), //
		YANGON("Yangon"), //
		YEKATERINBURG("Yekaterinburg"), //
		YEREVAN("Yerevan"), //
		;

		private final String id;

		private ASIA() {
			this.id = name();
		}

		private ASIA(String id) {
			this.id = id;
		}

		@Override
		public String id() {
			return "Asia/" + this.id;
		}
	}

	static enum ATLANTIC implements TimeZone {
		AZORES("Azores"), //
		BERMUDA("Bermuda"), //
		CANARY("Canary"), //
		CAPE_VERDE("Cape_Verde"), //
		FAROE("Faroe"), //
		MADEIRA("Madeira"), //
		REYKJAVIK("Reykjavik"), //
		SOUTH_GEORGIA("South_Georgia"), //
		ST_HELENA("St_Helena"), //
		STANLEY("Stanley"), //
		;

		private final String id;

		private ATLANTIC(String id) {
			this.id = id;
		}

		@Override
		public String id() {
			return "Atlantic/" + this.id;
		}
	}

	static enum AUSTRALIA implements TimeZone {
		ADELAIDE("Adelaide"), //
		BRISBANE("Brisbane"), //
		BROKEN_HILL("Broken_Hill"), //
		DARWIN("Darwin"), //
		EUCLA("Eucla"), //
		HOBART("Hobart"), //
		LINDEMAN("Lindeman"), //
		LORD_HOWE("Lord_Howe"), //
		MELBOURNE("Melbourne"), //
		PERTH("Perth"), //
		SYDNEY("Sydney"), //
		;

		private final String id;

		private AUSTRALIA(String id) {
			this.id = id;
		}

		@Override
		public String id() {
			return "Australia/" + this.id;
		}
	}

	static enum EUROPE implements TimeZone {
		AMSTERDAM("Amsterdam"), //
		ANDORRA("Andorra"), //
		ASTRAKHAN("Astrakhan"), //
		ATHENS("Athens"), //
		BELGRADE("Belgrade"), //
		BERLIN("Berlin"), //
		BRATISLAVA("Bratislava"), //
		BRUSSELS("Brussels"), //
		BUCHAREST("Bucharest"), //
		BUDAPEST("Budapest"), //
		BUSINGEN("Busingen"), //
		CHISINAU("Chisinau"), //
		COPENHAGEN("Copenhagen"), //
		DUBLIN("Dublin"), //
		GIBRALTAR("Gibraltar"), //
		GUERNSEY("Guernsey"), //
		HELSINKI("Helsinki"), //
		ISLE_OF_MAN("Isle_of_Man"), //
		ISTANBUL("Istanbul"), //
		JERSEY("Jersey"), //
		KALININGRAD("Kaliningrad"), //
		KIEV("Kiev"), //
		KIROV("Kirov"), //
		LISBON("Lisbon"), //
		LJUBLJANA("Ljubljana"), //
		LONDON("London"), //
		LUXEMBOURG("Luxembourg"), //
		MADRID("Madrid"), //
		MALTA("Malta"), //
		MARIEHAMN("Mariehamn"), //
		MINSK("Minsk"), //
		MONACO("Monaco"), //
		MOSCOW("Moscow"), //
		OSLO("Oslo"), //
		PARIS("Paris"), //
		PODGORICA("Podgorica"), //
		PRAGUE("Prague"), //
		RIGA("Riga"), //
		ROME("Rome"), //
		SAMARA("Samara"), //
		SAN_MARINO("San_Marino"), //
		SARAJEVO("Sarajevo"), //
		SARATOV("Saratov"), //
		SIMFEROPOL("Simferopol"), //
		SKOPJE("Skopje"), //
		SOFIA("Sofia"), //
		STOCKHOLM("Stockholm"), //
		TALLINN("Tallinn"), //
		TIRANE("Tirane"), //
		ULYANOVSK("Ulyanovsk"), //
		UZHGOROD("Uzhgorod"), //
		VADUZ("Vaduz"), //
		VATICAN("Vatican"), //
		VIENNA("Vienna"), //
		VILNIUS("Vilnius"), //
		VOLGOGRAD("Volgograd"), //
		WARSAW("Warsaw"), //
		ZAGREB("Zagreb"), //
		ZAPOROZHYE("Zaporozhye"), //
		ZURICH("Zurich"), //
		;

		private final String id;

		private EUROPE(String id) {
			this.id = id;
		}

		@Override
		public String id() {
			return "Europe/" + this.id;
		}
	}

	static enum INDIAN implements TimeZone {
		ANTANANARIVO("Antananarivo"), //
		CHAGOS("Chagos"), //
		CHRISTMAS("Christmas"), //
		COCOS("Cocos"), //
		COMORO("Comoro"), //
		KERGUELEN("Kerguelen"), //
		MAHE("Mahe"), //
		MALDIVES("Maldives"), //
		MAURITIUS("Mauritius"), //
		MAYOTTE("Mayotte"), //
		REUNION("Reunion"), //
		;

		private final String id;

		private INDIAN(String id) {
			this.id = id;
		}

		@Override
		public String id() {
			return "Indian/" + this.id;
		}
	}

	static enum PACIFIC implements TimeZone {
		APIA("Apia"), //
		AUCKLAND("Auckland"), //
		BOUGAINVILLE("Bougainville"), //
		CHATHAM("Chatham"), //
		CHUUK("Chuuk"), //
		EASTER("Easter"), //
		EFATE("Efate"), //
		ENDERBURY("Enderbury"), //
		FAKAOFO("Fakaofo"), //
		FIJI("Fiji"), //
		FUNAFUTI("Funafuti"), //
		GALAPAGOS("Galapagos"), //
		GAMBIER("Gambier"), //
		GUADALCANAL("Guadalcanal"), //
		GUAM("Guam"), //
		HONOLULU("Honolulu"), //
		KIRITIMATI("Kiritimati"), //
		KOSRAE("Kosrae"), //
		KWAJALEIN("Kwajalein"), //
		MAJURO("Majuro"), //
		MARQUESAS("Marquesas"), //
		MIDWAY("Midway"), //
		NAURU("Nauru"), //
		NIUE("Niue"), //
		NORFOLK("Norfolk"), //
		NOUMEA("Noumea"), //
		PAGO_PAGO("Pago_Pago"), //
		PALAU("Palau"), //
		PITCAIRN("Pitcairn"), //
		POHNPEI("Pohnpei"), //
		PORT_MORESBY("Port_Moresby"), //
		RAROTONGA("Rarotonga"), //
		SAIPAN("Saipan"), //
		TAHITI("Tahiti"), //
		TARAWA("Tarawa"), //
		TONGATAPU("Tongatapu"), //
		WAKE("Wake"), //
		WALLIS("Wallis"), //
		;

		private final String id;

		private PACIFIC(String id) {
			this.id = id;
		}

		@Override
		public String id() {
			return "Pacific/" + this.id;
		}
	}
}
