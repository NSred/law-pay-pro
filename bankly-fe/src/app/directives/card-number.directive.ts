import {Directive, ElementRef, HostListener} from '@angular/core';

@Directive({
  selector: '[appCardNumber]',
  standalone: true
})
export class CardNumberDirective {

  constructor(private el: ElementRef) {}

  @HostListener('input', ['$event']) onInputChange(event: Event) {
    const inputElement = this.el.nativeElement;
    let initialValue = inputElement.value;

    // Remove non-numeric characters and format
    let formattedValue = initialValue.replace(/[^0-9]*/g, '').replace(/(\d{4})/g, '$1 ').trim();

    // Extract digits only for length check
    let digitsOnly = formattedValue.replace(/\s+/g, '');

    // Limit to 16 numeric characters (19 including spaces)
    if (digitsOnly.length > 16) {
      // Cut the string to 16 digits, then format with spaces
      digitsOnly = digitsOnly.substring(0, 16);
      formattedValue = digitsOnly.replace(/(\d{4})/g, '$1 ').trim();
    }

    // Update the input field only if the value has changed
    if (initialValue !== formattedValue) {
      inputElement.value = formattedValue;
      event.stopPropagation();
    }
  }
}
