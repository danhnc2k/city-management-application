export type Data = {
  wasteType: string;
  collectedAmount: number;
  unit: string;
  collectedOn: string;
};

export type HeadCell = {
  id: keyof Data;
  numeric: boolean;
  label: string;
};

export type WasteType = "Recyclable" | "Non-recyclable" | "Hazardous";
