import axios from "axios";

const BASE_URL = "http://localhost:8080/city-management-service";

const apiClient = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

export interface City {
  id: string;
  name: string;
  state: string;
  country: string;
  wasteCollectors: WasteCollector[];
}

export interface WasteCollector {
  name: string;
  email: string;
  wasteCollections: WasteCollection[];
}

export interface WasteCollection {
  collectedAmount: number;
  unit: string;
  collectedOn: string;
  wasteType: string;
}

export interface CreateCityResponse {
  cityId: string;
}

export const fetchCityData = async (cityId: string): Promise<City> => {
  try {
    console.log("Start to fetch city data for cityId: ", cityId);

    const response = await apiClient.get<City>("/v1/city/retrieve", {
      params: { city_id: cityId },
    });

    console.log("Fetched city data response: ", response.data);
    return response.data;
  } catch (error) {
    console.error("Error fetching city data: ", error);
    throw error;
  }
};

export const createCityData = async (
  cityRequest: City
): Promise<CreateCityResponse> => {
  try {
    console.log("Start to create city data for cityRequest: ", cityRequest);

    const response = await apiClient.post<CreateCityResponse>(
      "/v1/city/create",
      cityRequest
    );

    console.log("Created city data response: ", response.data);
    return response.data;
  } catch (error) {
    console.error("Error creating city data: ", error);
    throw error;
  }
};
