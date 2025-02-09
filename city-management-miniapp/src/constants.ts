import { HeadCell } from "./types";

export const headCells: HeadCell[] = [
  {
    id: "wasteType",
    numeric: false,
    label: "Waste type",
  },
  {
    id: "collectedAmount",
    numeric: true,
    label: "Amount",
  },
  {
    id: "unit",
    numeric: false,
    label: "Unit",
  },
  {
    id: "collectedOn",
    numeric: false,
    label: "Collect on",
  },
];


export const WasteTypeColorMapping = {
  Recyclable: "green",
  "Non-recyclable": "darkorange",
  Hazardous: "red",
} as const;
