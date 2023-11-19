import {FormControl} from "@angular/forms";

export function expiryDateValidator(control: FormControl): { [key: string]: any } | null {
  const value = control.value || '';
  const parts = value.split(' / ');

  if (parts.length === 2) {
    const month = parseInt(parts[0], 10);
    const year = parseInt(parts[1], 10);

    if (month < 1 || month > 12) {
      return { 'invalidMonth': true };
    }

    if (isNaN(year) || year < 1000 || year > 9999) {
      return { 'invalidYear': true };
    }
  } else {
    return { 'invalidFormat': true };
  }

  return null; // Return null if there are no errors
}
