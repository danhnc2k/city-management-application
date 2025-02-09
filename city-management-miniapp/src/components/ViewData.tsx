import {
  Button,
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  SelectChangeEvent,
  Typography,
} from "@mui/material";
import { Dispatch, SetStateAction } from "react";
import { City } from "../server/server";

export const ViewData = ({
  cities,
  cityId,
  setCity,
}: {
  cities: City[];
  cityId: string;
  setCity: Dispatch<SetStateAction<string>>;
}) => {
  const handleChange = (event: SelectChangeEvent) => {
    setCity(event.target.value);
  };
  return (
    <>
      <Typography variant="h6" component="h6" align="center" fontWeight="light">
        Choose a city
      </Typography>
      <form action={`/result/${cityId}`} method="GET">
        <FormControl fullWidth required>
          <InputLabel id="city-dropdown-label">City</InputLabel>
          <Select
            labelId="city-dropdown-label"
            id="city-dropdown"
            value={cityId}
            label="City"
            onChange={handleChange}
          >
            {(cities || []).map((city: City) => (
              <MenuItem key={city.id} value={city.id}>
                {city.name}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
        <Button
          variant="contained"
          color="primary"
          fullWidth
          type="submit"
          style={{ marginTop: "1rem" }}
        >
          Submit
        </Button>
      </form>
    </>
  );
};
