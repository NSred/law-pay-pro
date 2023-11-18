export interface Offer {
  id: string;
  name: string;
  price: number;
}

export const offersMock: Offer[] = [
  {
    id: '1',
    name: 'Taraba',
    price: 200
  },
  {
    id: '2',
    name: 'Luster',
    price: 350
  },
  {
    id: '3',
    name: 'Klaster',
    price: 1000
  }
]
