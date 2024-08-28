import { Filijala } from "./filijala";
import { Usluga } from "./usluga";

export class Korisnik_usluge {
  korisnik_uslugeID!: number;
  ime!: string;
  prezime!: string;
  maticni_broj!: string;
  usluga!: Usluga;
  filijala!: Filijala;
}