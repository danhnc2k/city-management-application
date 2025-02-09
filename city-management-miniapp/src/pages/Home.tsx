import {
  Box,
  Grid2 as Grid,
  Container,
  FormControl,
  InputLabel,
  Select,
  SelectChangeEvent,
  Typography,
  MenuItem,
  Button,
} from "@mui/material";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

type City = {
  id: string;
  name: string;
};

export const Home = () => {
  const [city, setCity] = useState<string>("");
  const [cities, setCities] = useState<City[]>([
    {
      name: "Hanoi",
      id: "hn",
    },
    {
      name: "Ho Chi Minh",
      id: "hcm",
    },
    {
      name: "Hue",
      id: "hue",
    },
  ]);

  const navigate = useNavigate();

  const handleChange = (event: SelectChangeEvent) => {
    setCity(event.target.value);
  };

  const handleSubmit = () => {
    alert(city);
    navigate("/result");
  };

  return (
    <Grid container alignItems="center" minHeight={"90vh"}>
      <Grid size={6}>
        <img
          src="https://www.telefonica.com/en/wp-content/uploads/sites/5/2024/01/pexels-photo-1108572-e1706694279617.jpeg"
          style={{ width: "-webkit-fill-available" }}
        />
      </Grid>
      <Grid size={6}>
        <Container>
          <Box sx={{ mt: 4, display: "flex", flexDirection: "column", gap: 1 }}>
            <Typography
              variant="h3"
              component="h3"
              align="center"
              fontWeight="bold"
            >
              City Data Management
            </Typography>
            <Typography
              variant="h6"
              component="h6"
              align="center"
              fontWeight="light"
            >
              Choose a city
            </Typography>
            <form onSubmit={handleSubmit}>
              <Box
                sx={{ mt: 4, display: "flex", flexDirection: "column", gap: 2 }}
              >
                <FormControl fullWidth required>
                  <InputLabel id="city-dropdown">City</InputLabel>
                  <Select
                    labelId="city-dropdown-label"
                    id="city-dropdown"
                    value={city}
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
                <Button variant="contained" color="primary" type="submit">
                  Submit
                </Button>
              </Box>
            </form>
          </Box>
        </Container>
      </Grid>
    </Grid>
  );
};
