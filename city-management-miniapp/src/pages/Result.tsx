import {
  Box,
  Button,
  Container,
  FormControl,
  FormControlLabel,
  FormLabel,
  Grid2 as Grid,
  Radio,
  RadioGroup,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TextField,
  Typography,
} from "@mui/material";
import { useCallback } from "react";
import { useParams } from "react-router-dom";
import { Data, WasteType } from "../types";
import { StyledTableCell, StyledTableRow } from "../styles";
import { WasteTypeColorMapping, headCells } from "../constants";

const samples = {
  name: "Sydney",
  state: "New South Wales",
  country: "Australia",
  wasteCollectors: [
    {
      name: "WasteX",
      email: "info@wastex.com",
      wasteCollections: [
        {
          collectedAmount: 1200,
          unit: "tons",
          collectedOn: "2024-02-08",
          wasteType: "Recyclable",
        },
      ],
    },
    {
      name: "WasteY",
      email: "info@wastex.com",
      wasteCollections: [
        {
          collectedAmount: 1200,
          unit: "tons",
          collectedOn: "2024-02-08",
          wasteType: "Non-recyclable",
        },

        {
          collectedAmount: 100,
          unit: "tons",
          collectedOn: "2024-02-08",
          wasteType: "Hazardous",
        },
      ],
    },
  ],
};

export const Result = () => {
  const { cityId } = useParams();
  const wasteCollectors = samples.wasteCollectors;

  const getWasteCollectionFromCollector = useCallback(
    (collector: string) => {
      const collection = wasteCollectors.find(
        (item) => item.name === collector
      )?.wasteCollections;
      return (
        <TableContainer>
          <Table>
            <TableHead>
              <StyledTableRow>
                {headCells.map((headCell) => (
                  <StyledTableCell
                    key={headCell.id}
                    align={headCell.numeric ? "right" : "left"}
                    padding={"normal"}
                  >
                    {headCell.label}
                  </StyledTableCell>
                ))}
              </StyledTableRow>
            </TableHead>
            {collection?.map((data: Data) => (
              <TableBody>
                {headCells.map((headCell) => (
                  <StyledTableCell
                    key={headCell.id}
                    align={headCell.numeric ? "right" : "left"}
                    padding={"normal"}
                  >
                    <p
                      style={{
                        color:
                          headCell.id === "wasteType"
                            ? WasteTypeColorMapping[data.wasteType as WasteType]
                            : "black",
                      }}
                    >
                      {data[headCell.id]}
                    </p>
                  </StyledTableCell>
                ))}
              </TableBody>
            ))}
          </Table>
        </TableContainer>
      );
    },
    [wasteCollectors]
  );

  return (
    <Container>
      {wasteCollectors.map((collector) => {
        return (
          <Box mt="1rem" mb="1rem" alignItems="center">
            <Typography variant="h5" align="center" fontWeight="bold">
              {collector.name}
            </Typography>
            <Typography style={{ fontSize: "1rem" }} align="center" mb="1.5rem">
              {collector.email}
            </Typography>
            <Box sx={{ display: "flex", flexWrap: "wrap" }} mb="1rem">
              <div>
                <FormControl>
                  <FormLabel id="waste-type-label">Type</FormLabel>
                  <RadioGroup
                    row
                    aria-labelledby="waste-type-label"
                    name="row-radio-buttons-group"
                  >
                    <FormControlLabel
                      value="recyclable"
                      control={<Radio />}
                      label="Recyclable"
                    />
                    <FormControlLabel
                      value="nỏnecyclable"
                      control={<Radio />}
                      label="Non-recyclable"
                    />
                    <FormControlLabel
                      value="hazardous"
                      control={<Radio />}
                      label="Hazardous"
                    />
                  </RadioGroup>
                </FormControl>
              </div>
              <div>
                <FormControl required>
                  <TextField
                    label="Amount"
                    id="waste-amount"
                    sx={{ m: 1, width: "25ch" }}
                  />
                </FormControl>
              </div>
              <div>
                <FormControl required>
                  <TextField
                    label="Unit"
                    id="waste-unit"
                    sx={{ m: 1, width: "25ch" }}
                    value="Tons"
                    slotProps={{
                      input: {
                        readOnly: true,
                      },
                    }}
                  />
                </FormControl>
              </div>
              <div
                style={{
                  display: "flex",
                  alignItems: "center",
                }}
              >
                <Button variant="text" color="secondary" type="submit">
                  Add
                </Button>
              </div>
            </Box>

            {getWasteCollectionFromCollector(collector.name)}
          </Box>
        );
      })}
    </Container>
  );
};
