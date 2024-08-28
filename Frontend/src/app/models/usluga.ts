import { Filijala } from "./filijala";
import { Korisnik_usluge } from "./korisnik_usluge";

export class Usluga {
  uslugaID!: number;
  naziv!: string;
  opis_usluge!: string;
  datum_ugovora!: Date;
  provizija!: number;
  filijala!: Filijala;
  korisnik_usluge!: Korisnik_usluge;
}