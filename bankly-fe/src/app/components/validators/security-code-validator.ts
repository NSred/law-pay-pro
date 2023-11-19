import {FormControl} from "@angular/forms";

export function cvcValidator(control: FormControl): { [key: string]: any } | null {
  const value = control.value || '';
  if (value.length !== 3) {
    return { 'invalidCvc': true };
  }
  return null; // Return null if there are no errors
}
