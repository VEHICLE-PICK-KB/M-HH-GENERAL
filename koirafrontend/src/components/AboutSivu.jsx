import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { Accordion, AccordionDetails, AccordionSummary, Typography } from '@mui/material';
import React, { useState } from "react";
import theme from '../Theme';

const AboutSivu = () => {
    const [isOpen1, setIsOpen1] = useState(false);
    const [isOpen2, setIsOpen2] = useState(false);
    const [isOpen3, setIsOpen3] = useState(false);
    const [isOpen4, setIsOpen4] = useState(false);

    const handleAccordionChange = (panel) => (event, isExpanded) => {
        switch (panel) {
            case 'panel1':
                setIsOpen1(isExpanded);
                break;
            case 'panel2':
                setIsOpen2(isExpanded);
                break;
            case 'panel3':
                setIsOpen3(isExpanded);
                break;
            case 'panel4':
                setIsOpen4(isExpanded);
                break;
            default:
                break;
        }
    };

    const accordionStyles = {
        backgroundColor: theme.palette.primary.main,
        color: '#fff',
        width: '70%',
//        margin: '0 auto',
    };

    const accordionDetailsStyles = {
        display: 'flex',
        flexDirection: 'column',
        textAlign: 'left', 
        width: '100%',
        margin: '0 auto',
    };
    

    return (
        <div style={{ marginTop: '20px', backgroundColor: '#e8f5e9' }}>
            <Accordion expanded={isOpen1} onChange={handleAccordionChange('panel1')} sx={accordionStyles}>
                <AccordionSummary expandIcon={<ExpandMoreIcon />} sx={{ color: '#fff' }}>
                    <Typography>Keitä olemme?</Typography>
                </AccordionSummary>
                <AccordionDetails sx={accordionDetailsStyles}>
                    <Typography>
                        Omppu&Rane on koirantarvike ja -vaatekauppa, jossa tuotteiden laatu ja toiminnallisuus
                        ovat huippuluokkaa. Haluamme varmistaa että koirasi pukeutuu hyvin, syö hyvin ja nauttii elämästään.

                        Y-Tunnus: 1234567-8
                        Perustettu: 04.02.1337
                        Perustajat: Omppu ja Rane
                    </Typography>
                </AccordionDetails>
            </Accordion>

            <Accordion expanded={isOpen2} onChange={handleAccordionChange('panel2')} sx={accordionStyles}>
                <AccordionSummary expandIcon={<ExpandMoreIcon />} sx={{ color: '#fff' }}>
                    <Typography>Tuotteistamme</Typography>
                </AccordionSummary>
                <AccordionDetails>
                    <Typography>
                        Meiltä saat kaiken kokoisille koirille takit, hatut, liivit ja muut vaatteet.
                        Valikoimastamme löytyvät myös lelut, ruoat ja muut herkut. 
                    </Typography>
                </AccordionDetails>
            </Accordion>

            <Accordion expanded={isOpen3} onChange={handleAccordionChange('panel3')} sx={accordionStyles}>
                <AccordionSummary expandIcon={<ExpandMoreIcon />} sx={{ color: '#fff' }}>
                    <Typography>Vaihdot ja palautukset</Typography>
                </AccordionSummary>
                <AccordionDetails>
                    <Typography>
                        Tuotteissamme on 30 päivän vaihto- ja palautusoikeus.
                    </Typography>
                </AccordionDetails>
            </Accordion>

            <Accordion expanded={isOpen4} onChange={handleAccordionChange('panel4')} sx={accordionStyles}>
                <AccordionSummary expandIcon={<ExpandMoreIcon />} sx={{ color: '#fff' }}>
                    <Typography>Hae meille töihin!</Typography>
                </AccordionSummary>
                <AccordionDetails>
                    <Typography>
                        Jos olet kiinnostunut hakemaan meille töihin lähetä aivoin hakemus ja CV meille sähköpostitse osoitteeseen omppu&rane@kvtkp.com.
                    </Typography>
                </AccordionDetails>
            </Accordion>
        </div>
    );
};
export default AboutSivu;