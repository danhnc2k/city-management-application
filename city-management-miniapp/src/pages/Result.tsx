import {
  Box,
  Container,
  Table,
  TableBody,
  TableContainer,
  TableHead,
  Typography,
} from "@mui/material";
import { useCallback, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Data, WasteType } from "../types";
import { StyledTableCell, StyledTableRow } from "../styles";
import { WasteTypeColorMapping, headCells } from "../constants";
import { fetchCityData, WasteCollector } from "../server/server";

export const Result = () => {
  const { cityId } = useParams();
  const [wasteCollectors, setWasteCollectors] = useState<WasteCollector[]>([]);

  useEffect(() => {
    fetchCityData(cityId || "")
      .then((response) => {
        setWasteCollectors(response.wasteCollectors);
      })
      .catch((error) => console.error("Failed to fetch:", error));
  }, []);

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
            <TableBody>
              {collection?.map((data: Data) => (
                <StyledTableRow>
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
                              ? WasteTypeColorMapping[
                                  data.wasteType as WasteType
                                ]
                              : "black",
                        }}
                      >
                        {data[headCell.id]}
                      </p>
                    </StyledTableCell>
                  ))}
                </StyledTableRow>
              ))}
            </TableBody>
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
            {getWasteCollectionFromCollector(collector.name)}
          </Box>
        );
      })}
    </Container>
  );
};
