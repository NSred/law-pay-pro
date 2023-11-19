import {FormControl} from "@angular/forms";

export function cardNumberValidator(control: FormControl): { [key: string]: any } | null {
  const value = control.value.replace(/\s+/g, ''); // Remove spaces
  if (value && value.length !== 16) {
    return { 'invalidCardNumber': true };
  }
  return null; // Return null if there are no errors
}
