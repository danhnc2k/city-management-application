import {
  Box,
  Button,
  Card,
  CardActions,
  CardContent,
  CardHeader,
  Container,
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  SelectChangeEvent,
  Typography,
} from "@mui/material";
import { useState } from "react";
import { Link } from "react-router-dom";

export const Home = () => {
  const [city, setCity] = useState<string>("Ha Noi");
  const [cities, setCities] = useState<string[]>([
    "Ho Chi Minh",
    "Ha Noi",
    "Hue",
  ]);

  const handleChange = (event: SelectChangeEvent) => {
    setCity(event.target.value);
  };

  const handleSubmit = () => {};

  return (
    <Card sx={{ maxWidth: 900, margin: "30px auto", border: "1px solid #356" }}>
      <CardHeader>Login</CardHeader>
      <CardContent>
        <Container maxWidth="xs">
          <Box
            component="form"
            onSubmit={handleSubmit}
            sx={{ mt: 4, display: "flex", flexDirection: "column", gap: 2 }}
          >
            <Typography variant="h5" component="h1" align="center">
              Enter City Data
            </Typography>

            <FormControl fullWidth>
              <InputLabel id="demo-simple-select-label">City</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                value={city}
                label="Age"
                onChange={handleChange}
              >
                {!!cities &&
                  cities.map((city) => (
                    <MenuItem value={city}>{city}</MenuItem>
                  ))}
              </Select>
            </FormControl>
          </Box>
        </Container>
      </CardContent>
      <CardActions>
        <Button type="submit" variant="contained" color="primary">
          Submit
        </Button>
      </CardActions>
    </Card>
  );
};
