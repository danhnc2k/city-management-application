import { Box, Button, FormControl, MenuItem, TextField } from "@mui/material";
import { useCallback, useState } from "react";
import { City, createCityData } from "../server/server";

export const AddData = ({ cities }: { cities: City[] }) => {
  const [companyName, setCompanyName] = useState<string>("");
  const [companyEmail, setCompanyEmail] = useState<string>("");
  const [cityName, setCityName] = useState<string>("");
  const [wasteAmount, setWasteAmount] = useState<number>(0);
  const [wasteType, setWasteType] = useState<string>("");
  const [wasteUnit, setWasteUnit] = useState<string>("");

  const handleOnSubmit = useCallback(
    (event: any) => {
      event.preventDefault();

      const createCityRequest: City = {
        name: cityName,
        wasteCollectors: [
          {
            name: companyName,
            email: companyEmail,
            wasteCollections: [
              {
                wasteType: wasteType,
                collectedAmount: wasteAmount,
                unit: wasteUnit,
                collectedOn: new Date().toUTCString(),
              },
            ],
          },
        ],
      };

      createCityData(createCityRequest)
        .then(() => alert("Successfully create city data!"))
        .catch(() => alert("Error while creating city data!"));
    },
    [companyName, companyEmail, cityName, wasteAmount, wasteType, wasteUnit]
  );

  return (
    <form onSubmit={handleOnSubmit}>
      <Box display={"flex"} mb="1rem" ml="1rem">
        <div>
          <FormControl required>
            <TextField
              label="Company name"
              id="company-name"
              name="companyName"
              sx={{ m: 1, width: "25ch" }}
              value={companyName}
              onChange={(event) => setCompanyName(event.target.value)}
            />
          </FormControl>
        </div>
        <div>
          <FormControl required>
            <TextField
              label="Company email"
              id="company-email"
              name="companyEmail"
              sx={{ m: 1, width: "25ch" }}
              value={companyEmail}
              onChange={(event) => setCompanyEmail(event.target.value)}
            />
          </FormControl>
        </div>
      </Box>
      <Box display={"flex"} mb="1rem" ml="1rem">
        <div>
          <FormControl fullWidth required>
            <TextField
              label="City"
              id="city"
              name="city"
              sx={{ m: 1, width: "25ch" }}
              value={cityName}
              onChange={(event) => setCityName(event.target.value)}
            />
          </FormControl>
        </div>
        <div>
          <FormControl fullWidth required>
            <TextField
              id="type"
              name="type"
              select
              label="Type"
              sx={{ m: 1, width: "25ch" }}
              value={wasteType}
              onChange={(event) => setWasteType(event.target.value)}
            >
              <MenuItem value="Recyclable">Recyclable</MenuItem>
              <MenuItem value="Non-recyclable">Non-recyclable</MenuItem>
              <MenuItem value="Hazardous">Hazardous</MenuItem>
            </TextField>
          </FormControl>
        </div>
      </Box>
      <Box display={"flex"} mb="1rem" ml="1rem">
        <div>
          <FormControl required>
            <TextField
              label="Amount"
              id="waste-amount"
              name="amount"
              type="number"
              sx={{ m: 1, width: "25ch" }}
              value={wasteAmount}
              onChange={(event) =>
                setWasteAmount(Number.parseInt(event.target.value))
              }
            />
          </FormControl>
        </div>
        <div>
          <FormControl required>
            <TextField
              id="waste-unit"
              name="unit"
              select
              label="Unit"
              sx={{ m: 1, width: "25ch" }}
              value={wasteUnit}
              onChange={(event) => setWasteUnit(event.target.value)}
            >
              <MenuItem value="tons">Tons</MenuItem>
            </TextField>
          </FormControl>
        </div>
      </Box>
      <Box display={"flex"} ml="1.5rem">
        <Button variant="contained" color="secondary" type="submit" fullWidth>
          Add
        </Button>
      </Box>
    </form>
  );
};
