import { Button, Container, Grid2 as Grid, Typography } from "@mui/material";
import { useState } from "react";
import { ViewData } from "../components/ViewData";
import { City } from "../server/server";
import { AddData } from "../components/AddData";

/* This will be enhance by sourcing data from API in the future*/
const CITY_LIST: City[] = [
  {
    name: "Melbourne",
    id: "d613f5b0-79e4-4f1c-b672-900221a0233b",
  } as City,
  {
    name: "Sydney",
    id: "ac3bf57c-ad79-4e85-b300-341f02a5ff33",
  } as City,
  {
    name: "Ho Chi Minh",
    id: "56efac11-c9d8-45ee-975a-a252c96f889b",
  } as City,
];

export const Home = () => {
  const [view, setView] = useState<string>("");
  const [cityId, setCityId] = useState<string>("");
  const [cities, setCities] = useState<City[]>(CITY_LIST);

  return (
    <Grid container alignItems="center" minHeight={"90vh"}>
      <Grid size={6}>
        <img
          src="https://www.telefonica.com/en/wp-content/uploads/sites/5/2024/01/pexels-photo-1108572-e1706694279617.jpeg"
          alt="reference"
          style={{ width: "-webkit-fill-available" }}
        />
      </Grid>
      <Grid size={6}>
        <Container>
          <Typography
            variant="h3"
            component="h3"
            align="center"
            fontWeight="bold"
          >
            City Data Management
          </Typography>
          <Grid container alignItems="center" spacing={2} m="1.5rem">
            <Grid size={6}>
              <Button
                variant="outlined"
                color="secondary"
                fullWidth
                onClick={() => {
                  setView("add");
                }}
              >
                Add data
              </Button>
            </Grid>
            <Grid size={6}>
              <Button
                variant="outlined"
                color="primary"
                fullWidth
                onClick={() => {
                  setView("view");
                }}
              >
                View data
              </Button>
            </Grid>
          </Grid>
          {view === "view" && (
            <ViewData cities={cities} cityId={cityId} setCity={setCityId} />
          )}
          {view === "add" && <AddData cities={cities} />}
        </Container>
      </Grid>
    </Grid>
  );
};
